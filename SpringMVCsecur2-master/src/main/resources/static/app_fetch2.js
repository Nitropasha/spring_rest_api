$(async function () {
    await getTableWithUsers();
    await getTableOneUser();
    getNewUserForm();
    getDefaultModal();
    addNewUser();
});

const userFetchService = {
    head: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Referer: null,
    },
    findAllUsers: async () => await fetch("http://localhost:8080/api/users"),
    findAdminUser: async () => await fetch("http://localhost:8080/api/info"),
    findOneUser: async (id) =>
        await fetch(`http://localhost:8080/api/users/${id}`),
    addNewUser: async (user) =>
        await fetch("http://localhost:8080/api/users", {
            method: "POST",
            headers: userFetchService.head,
            body: JSON.stringify(user),
        }),
    updateUser: async (user) =>
        await fetch(`http://localhost:8080/api/users`, {
            method: "PUT",
            headers: userFetchService.head,
            body: JSON.stringify(user),
        }),
    deleteUser: async (id) =>
        await fetch(`http://localhost:8080/api/users/${id}`, {
            method: "DELETE",
            headers: userFetchService.head,
        }),
};

async function getTableOneUser() {
    let table = $("#mainTableWithOneUser tbody");
    table.empty();

    await userFetchService
        .findAdminUser()
        .then((res) => res.json())
        .then((user) => {
            const roles = user.roles.map((role) => role.authority).join(", ");
            let tableFilling = `$(
      
               <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
              
                <td>${user.email}</td>
                <td>
                ${roles}
                </td>
             
              </tr>
          )`;
            table.append(tableFilling);
        });
}

async function getTableWithUsers() {
    let table = $("#mainTableWithUsers tbody");
    table.empty();

    await userFetchService
        .findAllUsers()
        .then((res) => res.json())
        .then((users) => {
            users.forEach((user) => {
              
                let tableFilling = `$(
                <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>
                </td>
                <td>
                  <!-- Кнопка Update -->
                  <button type="button" data-userid="${user.id}" data-action="edit"  class="btn btn-primary btn-sm"
                  data-toggle="modal" data-target="#someDefaultModal"> Edit</button>
                  <!-- Кнопка Delete -->
                  <button type="button" data-userid="${user.id}" data-action="delete" class="btn btn-danger btn-sm"
                  data-toggle="modal" data-target="#someDefaultModal"> Delete</button>
                       
                </td>
              </tr>
          )`;
                table.append(tableFilling);
            });
        });

// кнопки edit или delete
    $("#mainTableWithUsers")
        .find("button")
        .on("click", (event) => {
            let defaultModal = $("#someDefaultModal");

            let targetButton = $(event.target);
            let buttonUserId = targetButton.attr("data-userid");
            let buttonAction = targetButton.attr("data-action");

            defaultModal.attr("data-userid", buttonUserId);
            defaultModal.attr("data-action", buttonAction);
            defaultModal.modal("show");
        });
}

async function getNewUserForm() {
    let button = $(`#SliderNewUserForm`);
    let form = $(`#defaultSomeForm`);
    button.on("click", () => {
        if (form.attr("data-hidden") === "true") {
            form.attr("data-hidden", "false");
            form.show();
            button.text("Hide panel");
        } else {
            form.attr("data-hidden", "true");
            form.hide();
            button.text("Show panel");
        }
    });
}

// открытие и закрытие модальных окон
async function getDefaultModal() {
    $("#someDefaultModal")
        .modal({
            keyboard: true,
            backdrop: "static",
            show: false,
        })
        .on("show.bs.modal", (event) => {
            let thisModal = $(event.target);
            let userid = thisModal.attr("data-userid");
            let action = thisModal.attr("data-action");
            switch (action) {
                case "edit":
                    editUser(thisModal, userid);
                    break;
                case "delete":
                    deleteUser(thisModal, userid);
                    break;
            }
        })
        .on("hidden.bs.modal", (e) => {
            let thisModal = $(e.target);
            thisModal.find(".modal-title").html("");
            thisModal.find(".modal-body").html("");
            thisModal.find(".modal-footer").html("");
        });
}

async function deleteUser(modal, id) {
  let preuser = await userFetchService.findOneUser(id);
  let user = await preuser.json(); 
  let bodyForm = `
<form class="form-group" id="editUser">
    <label for="id" class="form-label">ID</label>
    <input type="text" class="form-control" id="id" name="id" value="${user.id}" disabled readonly><br>
    
    <label for="firstName" class="form-label">First Name</label>
    <input class="form-control" type="text" id="firstName" value="${user.firstName}" disabled readonly><br>
    
    <label for="lastName" class="form-label">Last Name</label>
    <input class="form-control" type="text" id="lastName" value="${user.lastName}"disabled  readonly><br>
    
    <label for="password" class="form-label">Password</label>
    <input class="form-control" type="password" id="password" disabled  readonly><br>
    
    <label for="age" class="form-label">Age</label>
    <input class="form-control" id="age" type="number" value="${user.age}" disabled readonly><br>
    
    <label for="email" class="form-label">Email</label>
    <input class="form-control" id="email" type="text" value="${user.email}" disabled readonly><br>
    
    
</form>

  `;
  modal.find(".modal-body").html(bodyForm);
  modal.find(".modal-title").html("Delete user");

  let editButton = `<button class="btn btn-outline-success" id="deleteButton">Delete</button>`;
  let closeButton = `<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`;
  modal.find(".modal-footer").html(editButton + closeButton);
// Обработчик кнопки редактирования
$("#deleteButton").on("click", async () => {
  let id = modal.find("#id").val().trim();
  let firstName = modal.find("#firstName").val().trim();
  let lastName = modal.find("#lastName").val().trim();
  let password = modal.find("#password").val().trim();
  let age = modal.find("#age").val().trim();
  let email = modal.find("#email").val().trim();
 

  const response = await userFetchService.deleteUser(id);

  if (response.ok) {
      getTableWithUsers();
      modal.modal("hide");
      modal.find(".modal-title").html("");
      modal.find(".modal-body").html("User was deleted");
      let closeButton = `<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`;
      modal.find(".modal-footer").append(closeButton);
  } else {
      let body = await response.json();
      let alert = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert" id="sharaBaraMessageError">
                ${body.info}
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                   <span aria-hidden="true">&times;</span>
                  </button>
            </div>`;
      modal.find(".modal-body").prepend(alert);
  }
});

  
}


async function editUser(modal, id) {
    let preuser = await userFetchService.findOneUser(id);
    let user = await preuser.json(); // Получаем JSON данные

// Получаем роли пользователя в виде массива строк, например: ["USER", "ADMIN"]
    const userRoles = Array.isArray(user.roles)
        ? user.roles.map((role) => role.authority)
        : [];

// Все доступные роли, например: [{id: 1, authority: "USER"}, {id: 2, authority: "ADMIN"}]
    const allRoles = [
        {id: 1, authority: "USER"},
        {id: 2, authority: "ADMIN"},
    ];

// Создаем элементы чекбоксов для каждой роли в `allRoles`
    const rolesOptions = allRoles
        .map((role) => {
// Проверяем, есть ли у пользователя текущая роль, чтобы отметить чекбокс
            const isChecked = userRoles.includes(role.authority) ? "checked" : "";
            return `
      <div class="form-check">
          <input class="form-check-input" type="checkbox" value="${role.id}" id="role_${role.id}" ${isChecked}>
          <label class="form-check-label" for="role_${role.id}">
              ${role.authority}
          </label>
      </div>
  `;
        })
        .join("");

    modal.find(".modal-title").html("Edit user");

    let editButton = `<button class="btn btn-outline-success" id="editButton">Save</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`;
    modal.find(".modal-footer").html(editButton + closeButton);

    let bodyForm = `
<form class="form-group" id="editUser">
  <label for="id" class="form-label">ID</label>
  <input type="text" class="form-control" id="id" name="id" value="${user.id}" disabled><br>
  <label for="firstName" class="form-label">First Name</label>
  <input class="form-control" type="text" id="firstName" value="${user.firstName}"><br>
  <label for="lastName" class="form-label">Last Name</label>
  <input class="form-control" type="text" id="lastName" value="${user.lastName}"><br>
  <label for="password" class="form-label">Password</label>
  <input class="form-control" type="password" id="password"><br>
  <label for="age" class="form-label">Age</label>
  <input class="form-control" id="age" type="number" value="${user.age}"><br>
  <label for="email" class="form-label">Email</label>
  <input class="form-control" id="email" type="text" value="${user.email}"><br>
  <label for="roles" class="form-label">Roles</label>
  <div id="roles" class="form-group">
      ${rolesOptions}
  </div>
</form>
`;

    modal.find(".modal-body").html(bodyForm);

// Обработчик кнопки редактирования
    $("#editButton").on("click", async () => {
        let id = modal.find("#id").val().trim();
        let firstName = modal.find("#firstName").val().trim();
        let lastName = modal.find("#lastName").val().trim();
        let password = modal.find("#password").val().trim();
        let age = modal.find("#age").val().trim();
        let email = modal.find("#email").val().trim();

// Получаем выбранные роли
        let selectedRoles = Array.from(modal.find("#roles input:checked")).map(
            (checkbox) => ({
                id: checkbox.value,
                authority: checkbox.nextElementSibling.textContent,
            })
        );

        if (selectedRoles.length === 0) {
            alert("Please select at least one role."); // Сообщение, если роли не выбраны
            return;
        }

// Данные для отправки на сервер
        let data = {
            id: id,
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            age: age,
            roles: selectedRoles,
        };

        const response = await userFetchService.updateUser(data, id);

        if (response.ok) {
            getTableWithUsers();
            modal.modal("hide");
        } else {
            let body = await response.json();
            let alert = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert" id="sharaBaraMessageError">
                      ${body.info}
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                         <span aria-hidden="true">&times;</span>
                        </button>
                  </div>`;
            modal.find(".modal-body").prepend(alert);
        }
    });
}



async function addNewUser() {
    $("#addNewUserButton").click(async () => {
        let addUserForm = $("#defaultSomeForm");
        let firstName = addUserForm.find("#AddNewUserName").val().trim();
        let lastName = addUserForm.find("#AddNewUserLastName").val().trim();
        let email = addUserForm.find("#AddNewUserEmail").val().trim();
        let password = addUserForm.find("#AddNewUserPassword").val().trim();
        let username = addUserForm.find("#AddNewUserNamebd").val().trim();
        let age = addUserForm.find("#AddNewUserAge").val().trim();
// Получаем выбранные роли как массив объектов
        let selectedRoles = [];
        $("#rolesCheckboxes input:checked").each(function () {
            selectedRoles.push(JSON.parse($(this).val())); // Парсим значение как JSON
        });
        let data = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            username: username,
            password: password,
            age: age,
            roles: selectedRoles, // Добавляем роли в объект данных
        };
        const response = await userFetchService.addNewUser(data);
        if (response.ok) {
            getTableWithUsers();
            addUserForm.find("#AddNewUserName").val("");
            addUserForm.find("#AddNewUserLastName").val("");
            addUserForm.find("#AddNewUserEmail").val("");
            addUserForm.find("#AddNewUserPassword").val("");
            addUserForm.find("#AddNewUserAge").val("");
            addUserForm.find("#AddNewUserNamebd").val("");
            $("#rolesCheckboxes input:checkbox").prop("checked", false);
// Переключаемся обратно на вкладку с таблицей пользователей
            $("#users-tab").tab("show");
        } else {
            let body = await response.json();
            let alert = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert" id="sharaBaraMessageError">
                        ${body.info}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>`;
            addUserForm.prepend(alert);
        }
    });
}

$(document).ready(function () {
// Обработчик для клика по "ADMIN"
    $("#adminLink").on("click", function (e) {
        e.preventDefault();
        $("#adminContent").removeClass("d-none"); // Показать ADMIN контент
        $("#userContent").addClass("d-none"); // Скрыть USER контент
        console.log("ADMIN");
// Добавляем класс active для ADMIN
        $("#adminLink").addClass("active");
        $("#userLink").removeClass("active");
    });

// Обработчик для клика по "USER"
    $("#userLink").on("click", function (e) {
        e.preventDefault();
        $("#userContent").removeClass("d-none"); // Показать USER контент
        $("#adminContent").addClass("d-none"); // Скрыть ADMIN контент
        console.log("USER");

// Добавляем класс active для USER
        $("#userLink").addClass("active");
        $("#adminLink").removeClass("active");
    });
});

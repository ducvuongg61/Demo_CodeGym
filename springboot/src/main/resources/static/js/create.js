function saveUser() {
    const user = {
        id : $("#id").val(),
        name: $("#name").val(),
        age: $("#age").val(),
        address: $("#address").val()
    };
    $.ajax({
        url: "/api/user/create",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function(data) {
            alert(data);
        }
    });
}
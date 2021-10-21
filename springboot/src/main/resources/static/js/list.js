function searchByName() {
    const name = $("#name").val();
    $.ajax({
        url: "/api/user/searchByName",
        type: "get",
        data: {
            name : name
        },
        success: function(data) {
            // alert(JSON.stringify(data));
            var result =
                `<tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Address</th>
                    <th>Detail</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>`;
            for (var i = 0; i < data.length; i++) {
                result +=
                    `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].id}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].age}</td>
                        <td>${data[i].address}</td>
                        <td><a href="/user/detail/${data[i].id}">Detail</a></td>
                        <td><a href="/user/edit/${data[i].id}">Edit</a></td>
                        <td><a href="/user/delete/${data[i].id}">Delete</a></td>
                    </tr>`
            }
            $("#result").html(result);
        }
    });
}
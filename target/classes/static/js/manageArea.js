$(document).ready(function () {
    $.ajax({

        url: "http://localhost:8080/admin/fetchArea",
        type: "Get",
        success: function (response) {
            console.log(response)
            var htmlbody = '';
            for (let i = 0; i < response.length; i++) {
                var json = response[i];
                htmlbody += '<tr>'
                htmlbody += '<th scope="row">' + (i + 1) + '</th>'
                htmlbody += '<td>' + json.city.cityName + '</td>'
                htmlbody += '<td>' + json.areaName + '</td>'
                htmlbody += '<td className="cell_class">' + json.areaDescription + '</td>'
                htmlbody += '<td>'
                htmlbody += '<button class="btn btn-success" type="button" onclick="updateArea(' + json.areaId + ')"><i class="fa-solid fa-pen-to-square"></i>'
                htmlbody += '</button>'
                htmlbody += '<button class="btn btn-danger" type="button" onclick="deleteArea(' + json.areaId + ')"><i class="fa-regular fa-trash-can"></i>'
                htmlbody += '</button>'
                htmlbody += '</td>'
                htmlbody += '</tr>'
            }
            $("#tableData").html(htmlbody);
        }
    })
})

function updateArea(areaId) {
    console.log(areaId);
    var area = {
        "areaId": areaId
    }
    $.ajax({
        url: "http://localhost:8080/admin/getArea",
        type: "Get",
        data: area,

        success: function (response) {
            console.log(response)
            $(".heading").text("Update Area");
            $(".city-details-data").hide();
            $(".add-area-container").show();

            fetchCities(response.city.cityId);
            $("#areaId").val(response.areaId);
            $("#areaName").attr("value", response.areaName);
            $("#areaDesc").text(response.areaDescription);

        }
    })
}

function deleteArea(areaId) {
    console.log(areaId);
    var area = {
        "areaId": areaId
    }
    $.ajax({
        url: "http://localhost:8080/admin/deleteArea",
        type: "Get",
        data: area,

        success: function (response) {
            location.reload();

        }
    })
}

function fetchCities(cityId) {
    $.ajax({
        url: "http://localhost:8080/admin/fetchCity",
        type: "Get",
        success: function (resp) {
            var html = '';
            for (let i = 0; i < resp.length; i++) {
                html += '<option value="' + resp[i].cityId + '">' + resp[i].cityName + '</option>'
            }
            $("#ctyName").html(html);
            if (cityId != undefined) {
                $("#ctyName").val(cityId);
            }

        }
    })
}

$(function () {
    console.log("Manage city");
    $(".add-area-container").hide();
    $(".addcity").click(function (event) {
        $(".city-details-data").hide();
        $(".add-area-container").show();
        fetchCities(undefined);
    })


    $(".add-area-form").validate({
        rules: {
            areaName: {
                required: true
            },
            areaDesc: {
                required: true
            }
        },
        messages: {
            areaName: "City name is required.",
            areaDesc: "City Description is required."
        },
        submitHandler: function submitForm(form) {
            console.log("Submit");
            var json = {

                "areaName": $("#areaName").val(),
                "areaDescription": $("#areaDesc").val(),
                "cityId": $("#ctyName").val()
            }
            console.log(json)
            var areaId = $("#areaId").val();
            console.log(areaId);
            if (parseInt(areaId) == 0) {
                $.ajax({

                    url: "http://localhost:8080/admin/add-area",
                    type: "post",
                    data: JSON.stringify(json),
                    contentType: "application/json",
                    success: function (response) {

                        location.reload();
                        $(".city-details-data").show();
                        $(".add-area-container").hide();

                    },
                    error: function (response) {
                        console.log(response)
                        alert("Data is already present");


                    }
                })

            } else {
                json["areaId"] = areaId
                $.ajax({

                        url: "http://localhost:8080/admin/update-area",
                        type: "post",
                        data: JSON.stringify(json),
                        contentType: "application/json",
                        success: function (resp) {
                            alert(resp)
                            location.reload()
                        },
                        error: function (resp) {

                            alert(resp);
                        }
                    }
                )
            }

        }
    })
})
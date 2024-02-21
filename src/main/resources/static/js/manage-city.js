$(document).ready(function (){
    $.ajax({

        url: "http://localhost:8080/admin/fetchCity",
        type: "Get",
        success: function (response) {
            console.log(response)
            var htmlbody='';
            for (let i = 0; i < response.length; i++) {
                var json=response[i];
                htmlbody+="<tr>";

                htmlbody+='<th scope="row">'+(i+1)+'</th>'
                htmlbody+='<td>'+json.cityName+'</td>'
                htmlbody+='<td class="cell_class">'+json.cityDescription+'</td>'
                htmlbody+='<td>'
                    htmlbody+='<button class="btn btn-success m-2"  type="button" onclick="updateCity('+json.cityId+')"><i class="fa-solid fa-pen-to-square"></i>'
                    htmlbody+='</button>'
                    htmlbody+='<button class="btn btn-danger" type="button" onclick="deleteCity('+json.cityId+')"><i class="fa-regular fa-trash-can"></i>'
                    htmlbody+='</button>'
                    htmlbody+='</td>'
                htmlbody+='</tr>'
            }
            $("#tableData").html(htmlbody);
        }
    })
})
function updateCity(cityId){
    console.log(cityId);
    var city={
        "cityId":cityId
    }
    $.ajax({
        url: "http://localhost:8080/admin/getCity",
        type:"Get",
        data: city,

        success:function (response){
            console.log(response)
            $(".heading").text("Update City");
            $(".add-city-container").show();
            $(".city-details-data").hide();
            $("#cityId").val(""+response.cityId);
            $("#ctyName").val(response.cityName)

            $("#ctyDesc").text(response.cityDescription);

        }
    })
}

function deleteCity(cityId){
    console.log(cityId);
    var city={
        "cityId":cityId
    }
    $.ajax({
        url: "http://localhost:8080/admin/deleteCity",
        type:"Get",
        data: city,

        success:function (response){
           location.reload();

        }
    })
}

$(function(){
    console.log("Manage city");
    $(".add-city-container").hide();
    $(".addcity").click(function (event) {
        $(".city-details-data").hide();
        $(".add-city-container").show();
    })

    $(".add-city-form").validate({
        rules:{
            ctyName:{
                required:true
            },
            ctyDesc:{
                required: true
            }
        },
        messages:{
            ctyName: "City name is required.",
            ctyDesc: "City Description is required."
        },
        submitHandler: function submitForm(form) {
            console.log("Submit");
            var json={
                "cityName": $("#ctyName").val(),
                "cityDescription" : $("#ctyDesc").val()

            }

            console.log(json)
            if(parseInt($("#cityId").val())==0) {
                $.ajax({

                    url: "http://localhost:8080/admin/add-city",
                    type: "post",
                    data: JSON.stringify(json),
                    contentType: "application/json",
                    success: function (response) {
                        alert("Data added successfully");
                        location.reload();
                        $(".city-details-data").show();
                        $(".add-city-container").hide();

                    },
                    error: function (response) {
                        console.log(response)
                        alert("Data is already present");
                        $(".city-details-data").hide();
                        $(".add-city-container").show();

                    }
                })
            } else {
                json["cityId"]=$("#cityId").val();
                console.log(json);
                $.ajax({
                    url:"http://localhost:8080/admin/update-city",
                    type:"post",
                    data:JSON.stringify(json),
                    contentType: "application/json",
                    success: function (response){
                        alert(response);
                        location.reload();
                    },
                    error:function(response){
                        alert(response);

                    }
                })
            }
        }
    })
})
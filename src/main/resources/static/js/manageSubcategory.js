$(document).ready(function (){
    $.ajax({

        url: "http://localhost:8080/admin/fetchSubcategory",
        type: "Get",
        success: function (response) {
            console.log(response)
            var htmlbody='';
            for (let i = 0; i < response.length; i++) {
                var json=response[i];
                htmlbody+='<tr>'
                htmlbody+='<th scope="row">'+(i+1)+'</th>'
                htmlbody+='<td>'+json.category.categoryName+'</td>'
                htmlbody+='<td>'+json.subcategoryName+'</td>'
                htmlbody+='<td className="cell_class">'+json.subcategoryDescription+'</td>'
                htmlbody+='<td>'
                htmlbody+='<button class="btn btn-success" type="button" onclick="updateSubcategory('+json.subcategoryId+')"><i class="fa-solid fa-pen-to-square"></i>'
                htmlbody+='</button>'
                htmlbody+= '<button class="btn btn-danger" type="button" onclick="deleteSubcategory('+json.subcategoryId+')"><i class="fa-regular fa-trash-can"></i>'
                htmlbody+='</button>'
                htmlbody+='</td>'
                htmlbody+= '</tr>'
            }
            $("#tableData").html(htmlbody);
        }
    })
})
function updateSubcategory(subcategoryId){
    console.log(subcategoryId);
    var subcategory = {
        "subcategoryId": subcategoryId
    }
    $.ajax({
        url: "http://localhost:8080/admin/get-subcategory",
        type: "Get",
        data: subcategory,

        success: function (response) {
            console.log(response)
            $(".heading").text("Update subcategory");
            $(".city-details-data").hide();
            $(".add-subcategory-container").show();

            fetchCategories(response.category.categoryId);
            $("#subcategoryId").val(response.subcategoryId);
            $("#subcategoryName").attr("value", response.subcategoryName);
            $("#subcategoryDesc").text(response.subcategoryDescription);

        }
    })
}
function fetchCategories(categoryId){
    $.ajax({
        url: "http://localhost:8080/admin/fetch-category",
        type: "Get",
        success: function (resp) {
            var html = '';
            for (let i = 0; i < resp.length; i++) {
                html += '<option value="' + resp[i].categoryId + '">' + resp[i].categoryName + '</option>'
            }
            $("#cagtegoryName").html(html);
            if (categoryId != undefined) {
                $("#cagtegoryName").val(categoryId);
            }

        }
    })
}
function deleteSubcategory(subcategoryId){
    console.log(subcategoryId);
    var subcategory={
        "subcategoryId":subcategoryId
    }
    $.ajax({
            url: "http://localhost:8080/admin/deleteSubcategory",
        type:"Get",
        data: subcategory,

        success:function (response){
            location.reload();

        }
    })
}
$(function(){
    console.log("Manage city");
    $(".add-subcategory-container").hide();
    $(".addcity").click(function (event) {
        $(".city-details-data").hide();
        $(".add-subcategory-container").show();
        fetchCategories(undefined);
    })

    $(".backBtn").click(function (event) {
        $(".city-details-data").show();
        $(".add-subcategory-container").hide();
        fetchCategories(undefined);
    })
    // $(".saveBtn").click(function (event) {
    //     $(".city-details-data").hide();
    //     $(".add-city-container").show();
    // })


    $(".add-subcategory-form").validate({
        rules:{
            subcategoryName:{
                required:true
            },
            subcategoryDesc:{
                required: true
            }
        },
        messages:{
            subcategoryName: "City name is required.",
            subcategoryDesc: "City Description is required."
        },
        submitHandler: function submitForm(form) {
            console.log("Submit");
            var json={

                "subcategoryName": $("#subcategoryName").val(),
                "subcategoryDescription" : $("#subcategoryDesc").val(),
                "categoryId":$("#cagtegoryName").val()
            }
            var subcategoryId = $("#subcategoryId").val();
            console.log(json)
            if(parseInt(subcategoryId)==0){
                $.ajax({

                    url:"http://localhost:8080/admin/add-subcategory",
                    type:"post",
                    data:JSON.stringify(json),
                    contentType : "application/json",
                    success:function (response) {
                        console.log(response);
                        location.reload();
                        $(".city-details-data").show();
                        $(".add-subcategory-container").hide();

                    },
                    // error:function (response){
                    //     console.log(response)
                    //     alert("Data is already present");
                    //
                    //     location.reload(true);
                    // }
                })
            }else{
                json["subcategoryId"]=subcategoryId;
                $.ajax({

                    url:"http://localhost:8080/admin/update-subcategory",
                    type:"post",
                    data:JSON.stringify(json),
                    contentType : "application/json",
                    success:function (response) {
                        console.log(response);
                        location.reload();
                        $(".city-details-data").show();
                        $(".add-subcategory-container").hide();

                    },
                    error:function (response){
                        console.log(response)
                        alert("Data is already present");


                    }
                })
            }

        }
    })
})
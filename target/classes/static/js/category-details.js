$(document).ready(function (){
    $.ajax({
        url: "http://localhost:8080/admin/fetch-category",
        type: "Get",
        success: function (response){
            console.log(response)
            var htmlbody='';
            for (let i = 0; i < response.length; i++) {
                var json=response[i];
                htmlbody+="<tr>";
                htmlbody+='<th scope="row">'+(i+1)+'</th>'
                htmlbody+='<td>'+json.categoryName+'</td>'
                htmlbody+='<td class="cell_class">'+json.categoryDescription+'</td>'
                htmlbody+='<td>'
                htmlbody+='<button class="btn btn-success"  type="button" onclick="updateCategory('+json.categoryId+')"><i class="fa-solid fa-pen-to-square"></i>'
                htmlbody+='</button>'
                htmlbody+='<button class="btn btn-danger" type="button" onclick="deleteCategory('+json.categoryId+')"><i class="fa-regular fa-trash-can"></i>'
                htmlbody+='</button>'
                htmlbody+='</td>'
                htmlbody+='</tr>'
            }
            $("#tableData").html(htmlbody);
        }
    })
})

function updateCategory(categoryId){
    var category={
        "categoryId":categoryId
    }
    $.ajax({
        url: "http://localhost:8080/admin/get-category",
        type:"Get",
        data: category,

        success:function (response){
            console.log(response)
            $(".heading").text("Update Category");
            $(".city-details-data").hide();
            $(".add-category-container").show();
            $("#categoryId").val(""+response.categoryId);
            $("#categoryName").val(response.categoryName)
            $("#categoryDesc").text(response.categoryDescription);

        }
    })
}
function deleteCategory(categoryId){
    console.log(categoryId);
    var category={
        "categoryId":categoryId
    }
    $.ajax({
        url: "http://localhost:8080/admin/delete-category",
        type:"Get",
        data: category,

        success:function (response){
            location.reload();

        }
    })
}
$(function(){
    console.log("Category city");
    $(".add-category-container").hide();
    $(".addcity").click(function (event) {
        $(".city-details-data").hide();
        $(".add-category-container").show();

    })

    $(".backBtn").click(function (event) {
        $(".city-details-data").show();
        $(".add-category-container").hide();

    })

    $(".add-category-form").validate({
        rules:{
            categoryName:{
                required:true
            },
            categoryDesc:{
                required: true
            }
        },
        messages:{
            categoryName: "Category name is required.",
            categoryDesc: "Category Description is required."
        },
        submitHandler: function submitForm(form) {
            console.log("Submit");
            var json={

                "categoryName": $("#categoryName").val(),
                "categoryDescription" : $("#categoryDesc").val(),

            }
            var catId = $("#categoryId").val();
            console.log(json)
            if(parseInt(catId) == 0){
                $.ajax({

                    url:"http://localhost:8080/admin/add-category",
                    type:"post",
                    data:JSON.stringify(json),
                    contentType : "application/json",
                    success:function (response) {
                        console.log(response);
                        location.reload();
                        $(".city-details-data").show();
                        $(".add-area-container").hide();

                    },
                    error:function (response){
                        console.log(response)
                        alert("Data is already present");


                    }
                })
            }else{
                json["categoryId"] = catId;
                console.log(json);
                $.ajax({

                    url:"http://localhost:8080/admin/update-category",
                    type:"post",
                    data:JSON.stringify(json),
                    contentType : "application/json",
                    success:function (response) {
                        console.log(response);
                        location.reload();
                        $(".city-details-data").show();
                        $(".add-area-container").hide();

                    },
                    error:function (response){
                       alert(response.messages)
                    }
                })
            }

        }
    })
})
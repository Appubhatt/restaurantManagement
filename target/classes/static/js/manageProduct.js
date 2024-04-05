$(function (){
    var data={
        "restEmail":sessionStorage.getItem("restEmail")
    }
    $.ajax({
        url:"http://localhost:8080/restaurant/fetchAllProducts",
        type:"post",
        data:data,
        success:function (resp){
            console.log(resp);
            var html='';
            for (let i = 0; i < resp.length; i++) {
                html+='<tr>'
                html+='<th scope="row">'+(i+1)+'</th>'
                html+='<td>'+resp[i].category.categoryName+'</td>'
                html+='<td class="cell_class">'+resp[i].subCategory.subcategoryName+'</td>'
                html+='<td>'+resp[i].productName+'</td>'
                html+='<td>'
                var image=resp[i].productImage;
                console.log(typeof image)
                html+='<button class="btn btn-success" type="button" onclick="displayImage(\''+image+'\')"><i class="fa-solid fa-eye"></i></button>'
                html+='</td>'
                html+='<td>'+resp[i].productPrice+'</td>'
                html+='<td>'+resp[i].productDescription+'</td>'
                html+='<td>'
                html+='<button class="btn btn-success" type="button"><i class="fa-solid fa-pen-to-square"></i>'
                html+='</button>'
                html+='<button class="btn btn-danger" type="button"><i class="fa-regular fa-trash-can"></i>'
                html+='</button>'
                html+='</td>'
                html+='</tr>'
            }
            $("#tableData").html(html);
        }
    })

    $(".add-product-container").hide();
    $(".addProduct").click(function (event) {
        $(".city-details-data").hide();
        $(".add-product-container").show();
    })
    $(".backBtn").click(function (event) {
        $(".city-details-data").show();
        $(".add-product-container").hide();
    })

    var html = '<option value="">Select Option</option>';
    $("#subcategory").html(html);
    $.ajax({
        url: "http://localhost:8080/admin/fetch-category",
        type: "Get",
        success: function (resp) {
            console.log(resp)
            var html = '<option value="">Select Option</option>';
            for (let i = 0; i < resp.length; i++) {
                html += '<option value="' + resp[i].categoryId + '">' + resp[i].categoryName + '</option>'
            }
            $("#category").html(html);
        }
    })

    $("#category").change(function () {
        var data={
            "categoryId":$("#category").val()
        }
        $.ajax({
            url: "http://localhost:8080/admin/fetchBycategoryId",
            type: "Get",
            data:data,
            success: function (resp) {
                console.log(resp);
                var html = '<option value="">Select Option</option>';
                for (let i = 0; i < resp.length; i++) {
                    html += '<option value="' + resp[i].subcategoryId + '">' + resp[i].subcategoryName + '</option>'
                }
                $("#subcategory").html(html);
            }
        })
    })

    $(".add-product-form").validate({
        rules:{
            category:{
                required:true
            },
            subcategory:{
                required: true
            },
            productName:{
                required:true
            },
            productPrice:{
                required:true
            },
            productDesc:{
                required:true
            },
            productImage:{
                required:true
            }
        },
        messages:{
            category:{
                required:"Please select category"
            },
            subcategory:{
                required: "Please select subcategory"
            },
            productName:{
                required:"Please enter product name"
            },
            productPrice:{
                required:"Please enter product price"
            },
            productDesc:{
                required:"Please enter product description"
            },
            productImage:{
                required:"Please enter product image"
            }
        },
        submitHandler: function(form) {
            var fileInput =$('#productImage')[0].files[0];


            var json={
                "productName":$("#productName").val(),
                "productPrice":$("#productPrice").val(),
                "image":$("#productImage").val(),
                "description":$("#productDesc").val(),
                "categoryId":$("#category").val(),
                "subcategoryId":$("#subcategory").val(),
                "restEmail":sessionStorage.getItem("restEmail")
            }

            var formData = new FormData();
            formData.append("productImage",fileInput);
            formData.append("data",JSON.stringify(json));

            $.ajax({
                url:"http://localhost:8080/restaurant/saveProduct",
                type:"post",
                data:formData,
                contentType:false,
                processData: false,
                success : function (resp){
                    alert(resp)
                    location.reload()
                },
                error:function (resp){
                    alert(resp)
                }
            })
        }
    })
})

function displayImage(image){
    $("#display-productImage").css("display","block")
    $("#display-productImage").css("background-image","url(\"../images/"+image+"\")")
    $("#display-productImage").css("background-size","cover")
    $(".close-btn").click(function (){
        $("#display-productImage").css("display","none")
    })
    console.log(image);
}
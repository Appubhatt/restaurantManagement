$(function (){
    $(".add-offer-container").hide();

    $(".addProduct").click(function (){
        $(".add-offer-container").show();
        $(".city-details-data").hide();
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
    var data={
        "restEmail":sessionStorage.getItem("restEmail")
    }
    $.ajax({

        url:"http://localhost:8080/restaurant/fetchAllOffersEmail",
        type:"post",
        data:data,
        success: function (resp){
            console.log(resp)
            htmlbody='';
            for (let i = 0; i < resp.length; i++) {
                htmlbody+= '<tr>'
                    htmlbody+= '<th scope="row">'+ (i+1) +'</th>'
                    htmlbody+='<td>'+ resp[i].category.categoryName+'</td>'
                    htmlbody+= '<td className="cell_class">'+ resp[i].subCategory.subcategoryName+'</td>'
                    htmlbody+=' <td>'+resp[i].name+'</td>'
                    htmlbody+='<td>'+resp[i].description+'</td>'
                    htmlbody+= '<td>'+resp[i].startTime+'</td>'
                    htmlbody+= '<td>'+resp[i].endTime+'</td>'
                    htmlbody+= '<td>'+resp[i].discount+'</td>'
                    htmlbody+=' <td>'
                    htmlbody+=  ' <button class="btn btn-success m-2" type="button"><i class="fa-solid fa-pen-to-square"></i>'
                    htmlbody+=   ' </button>'
                    htmlbody+=    '<button class="btn btn-danger" type="button"><i class="fa-regular fa-trash-can"></i>'
                    htmlbody+=    ' </button>'
                    htmlbody+=  '</td>'

                    htmlbody+= '</tr>'
            }
            $("#tableData").html(htmlbody);
        },
        error:function (resp){
            console.log(resp)
        }
    })
    $(".add-offer-form").validate({
        rules:{
            category:{
                required:true
            },
            subcategory:{
                required: true
            },
            offerName:{
                required:true
            },
            offerDesc:{
                required:true
            },
            startTime:{
                required:true
            },
            endTime:{
                required:true
            },
            discount:{
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
            offerName:{
                required:"Please Enter name"
            },
            offerDesc:{
                required:"Please enter description"
            },
            startTime:{
                required:"Please select time"
            },
            endTime:{
                required:"Please select time"
            },
            discount:{
                required:"Please enter discount"
            }
        },
        submitHandler:function (form){
            var json={
                "name":$("#offerName").val(),
                "description":$("#offerDesc").val(),
                "startTime":$("#startTime").val(),
                "endTime":$("#endTime").val(),
                "discount": $("#discount").val(),
                "categoryId":$("#category").val(),
                "subcategoryId":$("#subcategory").val(),
                "restaurantEmail":sessionStorage.getItem("restEmail")
            }
            console.log(json);
            $.ajax({
                url:"http://localhost:8080/restaurant/saveOffer",
                type:"post",
                data:JSON.stringify(json),
                contentType:"application/json",
                success:function (resp) {
                    alert("Record added");

                    location.reload()
                },error:function (resp) {
                    console.log(resp)
                }
            })
        }
    })
})
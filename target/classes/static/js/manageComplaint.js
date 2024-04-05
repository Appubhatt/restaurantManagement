$(function (){
    $(".add-complaint-container").hide();
    var data={
        "restEmail":sessionStorage.getItem("restEmail")
    }
    $.ajax({
        url:"http://localhost:8080/restaurant/fetchAllEmail",
        type: "post",
        data:data,
        success:function (resp){
            console.log(resp);
            htmlbody='';
            for (let i = 0; i < resp.length; i++) {

                htmlbody+='<tr>'
                htmlbody+= '<th scope="row">'+(i+1)+'</th>'
                htmlbody+='<td>'+resp[i].subject+'</td>'
                htmlbody+='<td class="cell_class">'+ resp[i].description   +'</td>'
                htmlbody+='<td>'+resp[i].complaintDate+'</td>'
                htmlbody+= '<td>'+ resp[i].reply +'</td>'
                htmlbody+= '<td>'+ resp[i].replyDate+'</td>'
                htmlbody+= '<td><button type="button" class="btn btn-outline-primary"><i class="fa-solid fa-link"></i></button></td>'
                if(resp[i].status=="PENDING"){

                    htmlbody+= '<td><label class="rounded-pill bg-danger p-2 text-white">'+resp[i].status+'</label></td>'
                }else{
                    htmlbody+= '<td><label class="rounded-pill bg-success p-2 text-white">'+resp[i].status+'</label></td>'
                }
                htmlbody+= '</tr>'
            }
            $("#tableData").html(htmlbody);
        }
    })

    $(".addProduct").click(function (){
        $(".add-complaint-container").show();
        $(".city-details-data").hide();
    })
    $(".backBtn").click(function (){
        $(".add-complaint-container").hide();
        $(".city-details-data").show();
    })
    $(".add-complaint-form").validate({
        rules:{
            complaintSubject:{
                required:true
            },
            complaintDesc:{
                required: true
            },
            complaintAttachment:{
                required:true
            }
        },
        messages:{
            complaintSubject:{
                required:"Please enter subject"
            },
            complaintDesc:{
                required: "Please enter description"
            },
            complaintAttachment:{
                required:"Please enter attachment"
            }
        },
        submitHandler:function (form) {
            var attachment=$("#complaintAttachment")[0].files[0]
            var json={
                "subject":$("#complaintSubject").val(),
                "description" : $("#complaintDesc").val(),
                "restEmail" : sessionStorage.getItem("restEmail")
            }

            var formData = new FormData();
            formData.append("attachment",attachment);
            formData.append("data",JSON.stringify(json));

            $.ajax({
                url:"http://localhost:8080/restaurant/saveComplaint",
                data:formData,
                type:"post",
                contentType:false,
                processData:false,
                success:function (resp) {
                    console.log(resp)
                    location.reload()
                },
                error:function (resp) {
                    console.log(resp)
                }
            })
        }
    })
})
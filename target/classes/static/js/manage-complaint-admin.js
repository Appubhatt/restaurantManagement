$(function (){
    $.ajax({
        url:"http://localhost:8080/admin/fetchAll",
        type:"post",
        success:function (resp){
            console.log(resp);
            var htmlbody='';
            for (let i = 0; i < resp.length; i++) {
                htmlbody+='<tr>'
                htmlbody+=' <th scope="row">'+(i+1)+'</th>'
                htmlbody+= '<td>'+resp[i].restaurant.email+'</td>'
                htmlbody+=  '<td> '+resp[i].subject+'</td>'
                htmlbody+=  '<td> '+resp[i].description+'</td>'
                htmlbody+=  '<td> '+resp[i].complaintDate+'</td>'
                htmlbody+=  '<td> '+resp[i].reply+' </td>'
                htmlbody+=   '<td> '+resp[i].replyDate+' </td>'
                htmlbody+=  '<td> <button type="button" class="btn btn-outline-primary"><i class="fa-solid fa-link"></i></button> </td>'
                if(resp[i].status=="PENDING"){

                    htmlbody+=  '<td> <button class="btn"><label class="rounded-pill bg-danger p-2 text-white">'+resp[i].status+'</label></button> </td>'
                }
                htmlbody+= '</tr>'
            }
            $("#tableData").html(htmlbody);
        }

    })
})
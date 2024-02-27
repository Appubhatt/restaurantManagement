$(function (){
    $.ajax({
        url:"http://localhost:8080/admin/fetch-all-offers",
        type:"post",
        success:function (resp){
            console.log(resp);
            var htmlbody='';
            for (let i = 0; i < resp.length; i++) {
               htmlbody+='<tr>'
                htmlbody+='<th scope="row">'+(i+1)+'</th>'
                htmlbody+= '<td>'+resp[i].restaurant.restaurantName+'</td>'
                htmlbody+= '<td>'+resp[i].name+'</td>'
                htmlbody+= ' <td>'+resp[i].discount+'</td>'
                htmlbody+=  ' <td>'+resp[i].category.categoryName+'-'+resp[i].subCategory.subcategoryName+'</td>'
                htmlbody+=  '<td> '+resp[i].startTime+' </td>'
                htmlbody+=  '<td> '+resp[i].endTime+' </td>'
                htmlbody+= '</tr>'
            }
            $("#tableData").html(htmlbody);
        }
    })
})
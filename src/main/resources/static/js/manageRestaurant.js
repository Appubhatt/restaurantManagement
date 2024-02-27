$(function (){

    $.ajax({
        url:"http://localhost:8080/restaurant/fetch-all",
        type:"post",
        success:function (resp){
            console.log(resp);
           htmlbody='';
            for (let i = 0; i < resp.length; i++) {
                htmlbody+='<tr>'
                htmlbody+='<th scope="row">'+(i+1)+'</th>'
                htmlbody+= '<td>'+resp[i].restaurantName+'</td>'
                htmlbody+= '<td className="cell_class">'+resp[i].address+'</td>'
                htmlbody+= ' <td>'+resp[i].email+'</td>'
                htmlbody+=  '<td> '+resp[i].city.cityName+'</td>'
                htmlbody+=  '<td> '+resp[i].area.areaName+'</td>'
                htmlbody+= '</tr>'
            }
            $("#tableData").html(htmlbody);
        }
    })
})
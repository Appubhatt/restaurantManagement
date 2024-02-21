$().ready(function () {


    $(".registrationPageBtn").click(function () {
        $(".registration-container").show();
        $(".loginContainer").hide();
    })

    $(".loginPageBtn").click(function () {
        $(".registration-container").hide();
        $(".loginContainer").show();
    })

    $.ajax({
        url: "http://localhost:8080/admin/fetchCity",
        type: "Get",
        success: function (resp) {
            console.log(resp)
            var html = '<option value="">Select Option</option>';
            for (let i = 0; i < resp.length; i++) {
                html += '<option value="' + resp[i].cityId + '">' + resp[i].cityName + '</option>'
            }
            $("#city").html(html);
        }
    })

    $("#city").change(function () {
        var data={
            "cityId":$("#city").val()
        }
        $.ajax({
            url: "http://localhost:8080/admin/fetchBycityId",
            type: "Get",
            data:data,
            success: function (resp) {
                var html = '<option value="">Select Option</option>';
                for (let i = 0; i < resp.length; i++) {
                    html += '<option value="' + resp[i].areaId + '">' + resp[i].areaName + '</option>'
                }
                $("#area").html(html);
            }
        })
    })


    var value = $("#password").val();

    $.validator.addMethod("checklower", function (value) {
        return /[a-z]/.test(value);
    });
    $.validator.addMethod("checkupper", function (value) {
        return /[A-Z]/.test(value);
    });
    $.validator.addMethod("checkdigit", function (value) {
        return /[0-9]/.test(value);
    });

    $.validator.addMethod("checksc", function (value) {
        // Check if the value contains at least one digit and one special character
        return /[!@#$%^&*(),.?":{}|<>]/.test(value);
    });

    $("#login-page").validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {

                minlength: 6,
                maxlength: 16,
                required: true,
                checksc: true,
                checklower: true,
                checkupper: true,
                checkdigit: true
            }
        },
        messages: {
            email: {
                required: "Please enter email-id",
                email: "Please enter valid email"
            },
            password: {

                required: "Please enter password",
                minlength: "Minimum length should be 6",
                maxlength: "Password should not be larger than 16",

                checklower: "Need atleast 1 lowercase alphabet",
                checkupper: "Need atleast 1 uppercase alphabet",
                checkdigit: "Need atleast 1 digit"
            }
        },
        submitHandler: function (form) {
            var json = {
                "email": $("#email").val(),
                "password": $("#password").val()
            }

            $.ajax({
                data: JSON.stringify(json),
                url: "http://localhost:8080/login-submit",
                type: "post",
                contentType: "application/json",
                success: function (response) {
                    if (response.userType == "ADMIN") {

                        window.location = "http://localhost:8080/admin/admin-index";
                    }else{
                        window.location = "http://localhost:8080/restaurant/restaurant-dashboard";
                        sessionStorage.setItem("restEmail",response.email);
                    }
                    console.log(response);
                },
                error: function (response) {
                    alert(response);
                }
            })
        }

    });

    $("#registration-page").validate({
        rules: {
            restName: {
                required: true,
                minlength: 6,
                checklower: true,
                checkupper: true,
                checkdigit: true
            },
            restPassword: {
                required: true,
                minlength: 8,
                maxlength: 20,
                checklower: true,
                checkupper: true,
                checkdigit: true,
                checksc: true

            },
            restEmail:{
                required:true,
                email:true
            },
            restContact:{
                required:true,
                minlength:10,
                maxlength:10
            },
            restAddress:{
                required:true
            },
            city:{
                required:true
            },
            area:{
                required:true
            }
        },
        messages:{
            restName:{
                required: "Please enter name",
                minlength: "Minimum length should be 6",
                checklower: "One lower character is required",
                checkupper: "One upper character is required",
                checkdigit: "One Digit is required"
            },
            restEmail: {
                required: "Please enter email-id",
                email: "Please enter valid email"
            },
            restPassword: {

                required: "Please enter password",
                minlength: "Minimum length should be 8",
                checklower: "atleast 1 lowercase alphabet",
                checkupper: "atleast 1 uppercase alphabet",
                checkdigit: "atleast 1 digit",
                checksc: "Atleast 1 Special character",
                maxlength: "Password should not be larger than 20"
            },
        },
        submitHandler:function (form){
            var json={
                "restaurantName":$("#restName").val(),
                "password":$("#restPassword").val(),
                "email":$("#restEmail").val(),
                "contactNo" : $("#restContact").val(),
                "address": $("#restAddress").val(),
                "cityId":$("#city").val(),
                "areaId":$("#area").val(),
            }
            console.log(json)
            $.ajax({
                url:"http://localhost:8080/saveRestaurant",
                data:JSON.stringify(json),
                type:"post",
                contentType: "application/json",
                success:function (resp){

                    alert(resp.responseText)
                    location.reload();
                },error:function (resp){
                    alert(resp.responseText);
                }
            })
        }
    })

})
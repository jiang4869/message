/**
 * 校验表单
 *
 */
function checkUsername() {
    var username = $("#username").val();
    var regUsername = /^[a-zA-Z]\w{5,15}$/;

    var flag = regUsername.test(username);
    console.log(flag);
    if (flag) {

        /**
         * 过后删了
         */
        $("#username").css("border", "");
        $(".errmsg[for='username']").text("");
        // /*[[@{user/checkUserName}]]*/"user/checkUserName"
        console.log("user name " + username)
        $.post(/*[[@{user/checkUserName}]]*/"user/checkUserName", {"userName": username}, function (data) {
            if (data.flag == true) {
                $("#username").css("border", "");
                $(".errmsg[for='username']").text("");
            } else {
                $(".errmsg[for='username']").text("用户名已存在");

            }
        }, 'json')


    } else {
        $("#username").css("border", "1px solid red");
        $(".errmsg[for='username']").text("用户名格式错误");
    }

    console.log("checkusername " + flag);
    return flag;

}

function checkPassword() {
    var password = $("#password").val();
    var regPassword = /^\w{8,16}$/;

    var flag = regPassword.test(password);
    console.log(password)
    if (flag) {

        $("#password").css("border", "");
        $(".errmsg[for='password']").text("");


    } else {
        $("#password").css("border", "1px solid red");
        $(".errmsg[for='password']").text("密码长度应由8-16为数字或字符组成");
    }
    console.log("checkpassword " + flag);
    return flag;
}

function checkRepeatPassword() {
    var password = $("#password").val();
    var repeatPassword = $("#repeatPassword").val();

    var flag = password == repeatPassword;
    if (flag) {
        $("#repeatPassword").css("border", "");
        $(".errmsg[for='repeatPassword']").text("");
    } else {
        $("#repeatPassword").css("border", "1px solid red");
        $(".errmsg[for='repeatPassword']").text("两次密码不一致");
    }
    console.log("checkrepeatpassword " + flag);
    return flag;
}

function checkEmail() {
    var email = $("#email").val();
    // var regEmail = /^\w+@\w+\{.\w+$/;
    var regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
    var flag = regEmail.test(email);

    // alert(email)
    if (flag) {

        /**
         * 过后删了
         */
        $("#email").css("border", "");
        $(".errmsg[for='email']").text("");
        // /*[[@{hello}]]*/"hello"
        $.post(/*[[@{user/checkEmail}]]*/"user/checkEmail", {"email": email}, function (data) {
            console.log(data)

            if (data.flag == true) {
                $("#username").css("border", "");
                $(".errmsg[for='email']").text("");
            } else {
                $(".errmsg[for='email']").text("改邮箱已被使用");
                flag = false;
            }
        }, 'json')

    } else {
        $("#email").css("border", "1px solid red");
        $(".errmsg[for='email']").text("email格式错误");
    }

    console.log("checkeemail " + flag);

    return flag;
}


function checkCheckCode() {
    var checkCode = $("#checkCode").val();
    console.log("checkCode: " + checkCode);
    var flag = checkCode != "" && checkCode.length > 0;
    if (flag) {
        console.log("验证码");

        $.post(/*[[@{user/checkCheckCode}]]*/"user/checkCheckCode", {"checkCode": checkCode}, function (data) {
            if (data.flag == true) {
                $("#checkCode").css("border", "");
                $(".errmsg[for='checkCode']").text("");
                flag = true;
            } else {
                $("#checkCode").css("border", "1px solid red");
                $(".errmsg[for='checkCode']").text("验证码错误");
                flag = false;
            }
        }, 'json');


    } else {
        $("#checkCode").css("border", "1px solid red");
        $(".errmsg[for='checkCode']").text("请填写验证码");
        return flag;
    }
    console.log("checkcode " + flag);
    return flag;
}


function checkNickname() {
    var nickname = $("#nickname").val();

    var flag = nickname != null && nickname.length != 0;
    console.log("nick" + flag)

    if (flag) {

        /**
         * 过后删了
         */
        $("#nickname").css("border", "");
        $(".errmsg[for='nickname']").text("");


    } else {
        $("#nickname").css("border", "1px solid red");
        $(".errmsg[for='nickname']").text("昵称不能为空");
    }

    console.log("check nick name  " + flag);
    return flag;

}

function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}


$(function () {
    $("#email").blur(checkEmail);
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#repeatPassword").blur(checkRepeatPassword);
    $("#nickname").blur(checkNickname);
    $("#checkCode").blur(checkCheckCode);


    $("#register input").focus(function () {

        $(this).tooltip('show');
    })

    /**
     * 发送验证码
     */
    $("#sendCheckCode").click(function () {

        console.log("checkcode click")
        if (checkEmail()) {
            var email = $("#email").val();
            $(".errmsg[for='checkCode']").text("");
            console.log("check")
            $.post(/*[[@{user/getCheckCode}]]*/"user/getCheckCode", {"email": email}, function (data) {
                if (data.flag == true) {

                    alert("验证码已发送邮箱")
                }
            }, 'json')
        } else {
            $(".errmsg[for='checkCode']").text("请先填写邮箱");
        }

    });


    /**
     * 提交注册数据
     */
    $("#register").submit(function () {
        console.log("register");
        // User(uid=null, userName=null, nickName=null, password=aaaaaaaa, email=1121429190@qq.com, avatar=null)
        if (checkEmail() && checkCheckCode() && checkPassword() && checkRepeatPassword() && checkUsername() && checkNickname()) {

            var username = $("#username").val();
            var password = $("#password").val();
            var nickname = $("#nickname").val();
            var email = $("#email").val();

            console.log("register");

            $.post(/*[[@{user/register}]]*/"user/register",
                {
                    'userName': username,
                    'nickName': nickname,
                    'password': password,
                    'email': email
                }, function (data) {
                    console.log(data);
                    if (data.flag) {
                        var rootpath = getRootPath();
                        console.log(rootpath)
                        window.location = rootpath + "home";
                    } else {
                    }
                }, 'json')

        }
        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
        return false;
    })

})
var pathArray = window.location.pathname.split('/')
pathArray.pop();
pathArray.pop();
var basePath = pathArray.join("/");
swaggerUrl = basePath + ("/api/swagger.json");
$(document).ready(function () {


    $('#loginForm').on('submit', function login() {
        var data = {};
        data.username = $('#username').val();
        data.password = $('#password').val();
        var jsonStr = JSON.stringify(data);
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: basePath + "/api/users/login",
            data: jsonStr,
            dataType: "json",
            success: function (data, status) {
                setUserInfo(data);
            },
            error: function (xhr) {
                var errorInfo = $.parseJSON(xhr.responseText);
                alert(errorInfo.message);
            }
        });
        return false;
    });

    $('#logoutBtn').on('click', function () {
        $.getJSON("/api/users/logout", function (data) {
            unsetUserInfo(data)
        });
    });

    function setUserInfo(data) {
        var label = data.displayName ? data.displayName : "";
        label = label + "(id=" + data.id;
        if (data.localName) {
            label = label + ",localName=";
            label = label + data.localName;
        }
        if (data.mobile) {
            label = label + ", mobile=";
            label = label + data.mobile;
        }
        label = label + ")";
        if (data.group) {
            label = label + "@" + data.group.displayName;
            label = label + "(id=" + data.organization.id;
            if (data.group.localName) {
                label = label + ", localName=";
                label = label + data.group.localName;
            }
            label = label + ")";
        }
        $('#userLabel').html(label);
        $('#logoutPanel').show();
        $('#loginPanel').hide();
    }

    function unsetUserInfo() {
        $('#userLabel').html("");
        $('#logoutPanel').hide();
        $('#loginPanel').show();
    }

});
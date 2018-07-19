<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>chatroom</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link href="chatroom.css" rel="stylesheet">

<head>

</head>

<body>
    
    <div class="box">
        <div class="head">Chat Room</div>
        <div class="chat-sidebar">
            <div class="sidebar-name">
                <!-- Pass username and display name to register popup -->
                <a href="javascript:register_popup('narayan-prusty', 'Narayan Prusty');">
                    <img width="30" height="30" src="" />
                    <span>Narayan Prusty</span>
                </a>
            </div>
            <div class="sidebar-name">
                <a href="javascript:register_popup('qnimate', 'QNimate');">
                    <img width="30" height="30" src=""/>
                    <span>QNimate</span>
                </a>
            </div>
            <div class="sidebar-name">
                <a href="javascript:register_popup('qscutter', 'QScutter');">
                    <img width="30" height="30" src=""/>
                    <span>QScutter</span>
                </a>
            </div>
        </div>
    </div>

</body>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="chatroom.js"></script>
</html>

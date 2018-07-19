        $(document).ready(function() {
                $('.chat-sidebar').hide();
                // $('.msg-wrap').hide();
                $('.head').click(function() {
                $('.chat-sidebar').slideToggle(0);
            });

        });

        //this function can remove an array element.
        Array.remove = function(array, from, to) {
            var rest = array.slice((to || from) + 1 || array.length);
            array.length = from < 0 ? array.length + from : from;
            return array.push.apply(array, rest);
        };

        //this variable represents the total number of popups can be displayed according to the viewport width
        var total_popups = 0;

        //arrays of popups ids
        var popups = [];

        //this is used to close a popup
        function close_popup(id) {
            for (var i = 0; i < popups.length; i++) {
                if (id == popups[i]) {
                    $('#'+id+' .msg-wrap').show();
                    Array.remove(popups, i);
                    document.getElementById(id).style.display = "none";
                    calculate_popups();

                    return;
                }
            }
        } 

        //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
        function display_popups() {
            var right = 260;
            var i = 0;
            for (i; i < total_popups; i++) {
                if (popups[i] != undefined) {
                    var element = document.getElementById(popups[i]);
                    element.style.right = right + "px";
                    right = right + 240;
                    element.style.display = "block";
                }
            }

            for (var j = i; j < popups.length; j++) {
                var element = document.getElementById(popups[j]);
                element.style.display = "none";
            }
        }

        //creates markup for a new popup. Adds the id to popups array.
        function register_popup(id, name) {         

            for (var i = 0; i < popups.length; i++) {                
                //already registered. Bring it to front.
                if (id == popups[i]) {
                    $('#'+id+' .msg-wrap').show();
                    Array.remove(popups, i);
                    popups.unshift(id);
                    calculate_popups();

                    return;
                }
            }

            var element = '<div class="popup-box chat-popup" id="' + id + '">';
            element = element + '<div id="' + id + '" class="popup-head">';
            element = element + '<div class="popup-head-left">' + name + '</div>';
            element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\'' + id + '\');">&#10005;</a></div>';
            element = element + '<div style="clear: both"></div></div><div class="msg-wrap"><div id="' + id + '" class="popup-messages"><div class="msg-a">This is from A </div><div class="msg-b">This is from B, and its amazingly kool nah... i know it even i liked it :)</div><div class="msg-a">Wow, Thats great to hear from you man </div><div class="msg-push"></div></div>';
            element = element + '<div class="msg-footer"><textarea id="' + id + '" class="msg-input" rows="2"></textarea></div></div></div>';
      
            document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;

            popups.unshift(id);

            calculate_popups();

            $('.head').click(function() {
                $('.chat-sidebar').slideToggle(0);
            });

            $('.popup-head').click(function() {
                $('#' + $(this)[0].id + ' .msg-wrap').slideToggle(0);

            });

            $('textarea').keypress(
                function(e) {
                    if (e.keyCode == 13) { //enter
                        var msg = $(this).val();
                        event.preventDefault();
                        $(this).val('');
                        if (msg != '')
                            $('<div class="msg-b">' + msg + '</div>').insertBefore('#' + $(this)[0].id + ' .msg-push');
                        $('#' + $(this)[0].id + ' .popup-messages').scrollTop($('.popup-messages')[0].scrollHeight);
                }
            });            
        
        }

        //calculate the total number of popups suitable and then populate the toatal_popups variable.
        function calculate_popups() {
            var width = window.innerWidth;
            if (width < 540) {
                total_popups = 0;
            } else {
                width = width - 220;//sidebar width
                //320 is width of a single popup box
                total_popups = parseInt(width / 260);
            }

            display_popups();

        }

        //recalculate when window is loaded and also when window is resized.
        window.addEventListener("resize", calculate_popups);
        window.addEventListener("load", calculate_popups);

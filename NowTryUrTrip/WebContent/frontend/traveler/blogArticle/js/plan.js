		
$(document).ready(function() {
	$(".attractions>a").click(function(e) {
		$(".attractions>a.active").removeClass("active");
		$(this).attr("class", "list-group-item active");
		e.preventDefault();
		// 避免跳回頂端
	});

	$(".plainList>a").click(function(e) {
		$(".plainList>a.active").removeClass("active");
		$(this).attr("class", "list-group-item active");
		e.preventDefault();
		// 避免跳回頂端
	});

	$(".day_close").click(function(e) {
		$(this).parent().parent().hide();
		e.preventDefault();
	});

	$(".day_add").click(function(e) {
		$(schedule).parent().parent().hide();
		e.preventDefault();
	});
});

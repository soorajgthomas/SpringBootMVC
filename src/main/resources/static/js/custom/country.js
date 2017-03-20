$().ready(function(){


    $("#myModal").on("show", function () {
      $("body").addClass("modal-open");
    }).on("hidden", function () {
      $("body").removeClass("modal-open")
    });


    setTodayDate();


    $("#countryName").blur(function(){
        var alpha = /^[a-zA-Z]+(\s{0,1}[a-zA-Z0-9 ])*$/;
    	if (!$(this).val().match(alpha)) {
    	    if($(this).val()!="") {
    	    	alert("Invalid Name");
    			$("#countryName").val("");
    			$("#countryName").focus();
    			return false;
    		}
    	}
    });

});





$('.editId').click(function() {

    var nRow = $(this).parents('tr')[0];
	var form = $(nRow).attr("row-index");

	var jsonParams = {};
    jsonParams['id'] = form;
    jsonParams[csrfParameter] = csrfToken;

    $.ajax({
        url : "/getCountryById",
    	type : 'POST',
    	data : jsonParams,
    	cache : false,
    	beforeSend : function(xhr) {
    	    var csrftoken = $.cookie('CSRF-TOKEN');
            xhr.setRequestHeader("X-CSRF-TOKEN", csrftoken);
        },
        success : function(response) {
            var json = JSON.parse(response);

            $("#id").val(json.id);
            $("#status").val(json.status);
            if (json.createdTime != null) {
                var myDate1 = new Date(json.createdTime);
                var d2 = "";
                if (myDate1.getDate() < 10) {
                    d2 = "0"+ myDate1.getDate();
                } else {
                    d2 = myDate1.getDate();
                }
                var d1 = "";
                if (myDate1.getMonth() < 9) {
                    d1 = myDate1.getFullYear() + "-"+ "0"+ (myDate1.getMonth() + 1)+ "-"+ d2 ;
                } else {
                    d1 = myDate1.getFullYear() + "-" + (myDate1.getMonth() + 1)+ "-"+ d2  ;
                }
                $("#createdTime").val(d1);
            }

            $("#countryName").val(json.countryName);
            $("#countryShortName").val(json.countryShortName);
            $("#currencyName").val(json.currencyName);

        },
        error: function (e) {
            alert("failed");
        }
    });

});





$('.viewId').click(function() {

    var nRow = $(this).parents('tr')[0];
	var form = $(nRow).attr("row-index");

	var jsonParams = {};
    jsonParams['id'] = form;
    jsonParams[csrfParameter] = csrfToken;

    $.ajax({
        url : "/getCountryById",
    	type : 'POST',
    	data : jsonParams,
    	cache : false,
    	beforeSend : function(xhr) {
    	    var csrftoken = $.cookie('CSRF-TOKEN');
            xhr.setRequestHeader("X-CSRF-TOKEN", csrftoken);
        },
        success : function(response) {
            var json = JSON.parse(response);

            $("#vCountryName").text(json.countryName);
            $("#vCountryShortName").text(json.countryShortName);
            $("#vCurrencyName").text(json.currencyName).trigger('chosen:updated');

        },
        error: function (e) {
            alert("failed");
        }
    });

});



$('.deleteId').click(function() {

    var nRow = $(this).parents('tr')[0];
	var form = $(nRow).attr("row-index");

	var jsonParams = {};
    jsonParams['id'] = form;
    jsonParams[csrfParameter] = csrfToken;

    $.ajax({
        url : "/deactivateCountryById",
    	type : 'POST',
    	data : jsonParams,
    	cache : false,
    	beforeSend : function(xhr) {
    	    var csrftoken = $.cookie('CSRF-TOKEN');
            xhr.setRequestHeader("X-CSRF-TOKEN", csrftoken);
        },
        success : function(response) {

            if(response=="success") {
                alert("deleted");
            } else {
                alert("failed");
            }

        },
        error: function (e) {
            alert("failed");
        }
    });

});
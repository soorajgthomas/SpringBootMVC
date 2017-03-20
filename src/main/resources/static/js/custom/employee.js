$().ready(function(){


    $("#myModal").on("show", function () {
      $("body").addClass("modal-open");
    }).on("hidden", function () {
      $("body").removeClass("modal-open")
    });


    setTodayDate();


    $("#firstName").blur(function(){
        var alpha = /^[a-zA-Z]+(\s{0,1}[a-zA-Z0-9 ])*$/;
    	if (!$(this).val().match(alpha)) {
    	    if($(this).val()!="") {
    	    	alert("Invalid Fname");
    			$("#firstName").val("");
    			$("#firstName").focus();
    			 return false;
    		}
    	}
    });


    $("#phone").keydown(function(e) {
        if ($.inArray(e.keyCode, [ 46, 8, 9, 27, 13, 110, 190 ]) !== -1
    	        || (e.keyCode == 65 && e.ctrlKey === true)
    		    || (e.keyCode == 67 && e.ctrlKey === true)
    		    || (e.keyCode == 88 && e.ctrlKey === true)
    		    || (e.keyCode >= 35 && e.keyCode <= 39)) {
    		return;
    	}
    	if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57))
    			&& (e.keyCode < 96 || e.keyCode > 105)) {
    		e.preventDefault();
    	}
    });

});





function setTodayDate() {
	var mDate = new Date();
	var date="";
    if (mDate.getDate() < 10) {
        date = "0"+mDate.getDate();
    } else {
    	date = mDate.getDate();
    }
    var today = "";
    if(mDate.getMonth()<9) {
        today = mDate.getFullYear()+"-"+"0"+(mDate.getMonth() + 1)+"-"+date;
    } else {
    	today = month+"-"+(mDate.getMonth() + 1)+"-"+mDate.getFullYear();
    }

    $("#emiratesIdExpiry").val(today);
    $("#passportExpiry").val(today);
    $("#healthCardExpiry").val(today);
    $("#dateOfBirth").val(today);
    $("#dateOfJoin").val(today);
}





$('.editId').click(function() {

    var nRow = $(this).parents('tr')[0];
	var form = $(nRow).attr("row-index");

	var jsonParams = {};
    jsonParams['id'] = form;
    jsonParams[csrfParameter] = csrfToken;

    $.ajax({
        url : "/getEmployeeById",
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

            $("#firstName").val(json.firstName);
            $("#lastName").val(json.lastName);
            $("#nationality").val(json.nationality.id).trigger('chosen:updated');

            $("#district").val(json.district);
            $("#emiratesId").val(json.emiratesId);
            if (json.emiratesIdExpiry != null) {
            	var myDate1 = new Date(json.emiratesIdExpiry);
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
            	$("#emiratesIdExpiry").val(d1);
            }

            $("#employeeStatus").val(json.employeeStatus);
            $("#passportNo").val(json.passportNo);
            if (json.passportExpiry != null) {
                var myDate1 = new Date(json.passportExpiry);
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
                $("#passportExpiry").val(d1);
            }

            $("#healthCard").val(json.healthCard);
            if (json.healthCardExpiry != null) {
                var myDate1 = new Date(json.healthCardExpiry);
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
                $("#healthCardExpiry").val(d1);
            }
            if (json.dateOfBirth != null) { //parsing data
                var myDate1 = new Date(json.dateOfBirth);
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
                $("#dateOfBirth").val(d1);
            }

            $("#phone").val(json.phone);
            if (json.dateOfJoin != null) { //parsing data
                var myDate1 = new Date(json.dateOfJoin);
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
                $("#dateOfJoin").val(d1);
            }
            $("#profession").val(json.profession);

            $("#gender").val(json.gender).trigger('chosen:updated');

            $("#address").val(json.address);
            $("#address2").val(json.address2);


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
        url : "/getEmployeeById",
    	type : 'POST',
    	data : jsonParams,
    	cache : false,
    	beforeSend : function(xhr) {
    	    var csrftoken = $.cookie('CSRF-TOKEN');
            xhr.setRequestHeader("X-CSRF-TOKEN", csrftoken);
        },
        success : function(response) {
            var json = JSON.parse(response);

            $("#vFirstName").text(json.firstName);
            $("#vLastName").text(json.lastName);
            $("#vNationality").text(json.nationality.countryName);

            $("#vDistrict").text(json.district);
            $("#vEmiratesId").text(json.emiratesId);
            if (json.emiratesIdExpiry != null) {
            	var myDate1 = new Date(json.emiratesIdExpiry);
            	var d2 = "";
            	if (myDate1.getDate() < 10) {
            	    d2 = "0"+ myDate1.getDate();
            	} else {
            	    d2 = myDate1.getDate();
            	}

            	var d1 = "";
            	if (myDate1.getMonth() < 9) {
            	    d1 = d2 + "-" + "0" + (myDate1.getMonth() + 1)+ "-" + myDate1.getFullYear() ;
            	} else {
            		d1 = d2 + "-" + (myDate1.getMonth() + 1) + "-" + myDate1.getFullYear();
                }
            	$("#vEmiratesIdExpiry").text(d1);
            }

            $("#vEmployeeStatus").text(json.employeeStatus);
            $("#vPassportNo").text(json.passportNo);
            if (json.passportExpiry != null) {
                var myDate1 = new Date(json.passportExpiry);
                var d2 = "";
                if (myDate1.getDate() < 10) {
                    d2 = "0"+ myDate1.getDate();
                } else {
                    d2 = myDate1.getDate();
                }
                var d1 = "";
                if (myDate1.getMonth() < 9) {
                    d1 = d2 + "-" + "0" + (myDate1.getMonth() + 1) + "-" + myDate1.getFullYear();
                } else {
                    d1 = d2 + "-" + (myDate1.getMonth() + 1) + "-" +  myDate1.getFullYear();
                }
                $("#vPassportExpiry").text(d1);
            }

            $("#vHealthCard").text(json.healthCard);
            if (json.healthCardExpiry != null) {
                var myDate1 = new Date(json.healthCardExpiry);
                var d2 = "";
                if (myDate1.getDate() < 10) {
                    d2 = "0"+ myDate1.getDate();
                } else {
                    d2 = myDate1.getDate();
                }
                var d1 = "";
                if (myDate1.getMonth() < 9) {
                    d1 = d2 + "-" + "0" + (myDate1.getMonth() + 1) + "-" +  myDate1.getFullYear();
                } else {
                    d1 = d2 + "-" + (myDate1.getMonth() + 1)+ "-"+ myDate1.getFullYear();
                }
                $("#vHealthCardExpiry").text(d1);
            }
            if (json.dateOfBirth != null) {
                var myDate1 = new Date(json.dateOfBirth);
                var d2 = "";
                if (myDate1.getDate() < 10) {
                    d2 = "0"+ myDate1.getDate();
                } else {
                    d2 = myDate1.getDate();
                }
                var d1 = "";
                if (myDate1.getMonth() < 9) {
                    d1 = d2 + "-" + "0" + (myDate1.getMonth() + 1) + "-" + myDate1.getFullYear();
                } else {
                    d1 = d2 + "-" + (myDate1.getMonth() + 1) + "-" + myDate1.getFullYear();
                }
                $("#vDateOfBirth").text(d1);
            }

            $("#vPhone").text(json.phone);
            if (json.dateOfJoin != null) {
                var myDate1 = new Date(json.dateOfJoin);
                var d2 = "";
                if (myDate1.getDate() < 10) {
                    d2 = "0"+ myDate1.getDate();
                } else {
                    d2 = myDate1.getDate();
                }
                var d1 = "";
                if (myDate1.getMonth() < 9) {
                    d1 = d2 + "-"+ "0"+ (myDate1.getMonth() + 1)+ "-"+ myDate1.getFullYear();
                } else {
                    d1 = d2 + "-" + (myDate1.getMonth() + 1)+ "-"+  myDate1.getFullYear();
                }
                $("#vDateOfJoin").text(d1);
            }
            $("#vProfession").text(json.profession);

            $("#vGender").text(json.gender);

            $("#vAddress").text(json.address);
            $("#vAddress2").text(json.address2);


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
        url : "/deactivateEmployeeById",
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
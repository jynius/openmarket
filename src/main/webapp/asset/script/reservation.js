$(function() {

	var validatorOption = {
		"staying": {
			rules: {
				agree0: "required",
				chkindate: {
				  required: true,
				},
				chkoutdate: {
				  required: true,
				},
				rkname: "required",
				gkname: "required",
				rphone: {
				  required: true,
				},
				remail: {
				  required: true,
				  email: true
				}
			},
			messages: {
				agree0: "예약을 하기 위해서는 개인정보 수집과 이용에 동의가 필요합니다.",
				chkindate: {
				  required: "체크인 날짜를 입력해 주십시오.",
				},
				chkoutdate: {
				  required: "체크아웃 날짜를 입력해 주십시오.",
				},
				rkname: "예약자 이름을 입력해 주십시오.",
				gkname: "투숙자 이름을 입력해 주십시오.",
				rphone: {
				  required: "예약자 전화번호를 입력해 주십시오.",
				},
				remail: {
				  required: "이메일을 입력해 주십시오.",
				  email: "메일을 받을 수 있는, 유효한 메일주소를 입력해 주십시오."
				}
			}
		},
		"dining": {
			rules: {
				agree0: "required",
				rkname: "required",
				rphone: {
				  required: true,
				}
			},
			messages: {
				agree0: "예약을 하기 위해서는 개인정보 수집과 이용에 동의가 필요합니다.",
				rkname: "예약자 이름을 입력해 주십시오.",
				rphone: {
				  required: "예약자 전화번호를 입력해 주십시오.",
				}
			}
		},
		"meeting": {
			rules: {
				agree0: "required",
				rkname: "required",
				rphone: {
				  required: true,
				}
			},
			messages: {
				agree0: "예약을 하기 위해서는 개인정보 수집과 이용에 동의가 필요합니다.",
				rkname: "예약자 이름을 입력해 주십시오.",
				rphone: {
				  required: "예약자 전화번호를 입력해 주십시오.",
				}
			}
		},
		"wedding": {
			rules: {
				agree0: "required",
				rkname: "required",
				rphone: {
				  required: true,
				}
			},
			messages: {
				agree0: "예약을 하기 위해서는 개인정보 수집과 이용에 동의가 필요합니다.",
				rkname: "예약자 이름을 입력해 주십시오.",
				rphone: {
				  required: "예약자 전화번호를 입력해 주십시오.",
				}
			}
		}
	};

	$('.btn_reserv').click(function() {

//		alert('click!');
		var btnclass = null;
		var thisobj = $(this);
		$.each(["staying", "dining", "meeting", "wedding"], function(i, e) {
//			alert(thisobj.attr('id'));
			if(thisobj.hasClass(e)) {
				btnclass = e;
			}
		});
//		alert(btnclass);
		if(btnclass==null) {
			return;
		}

		var btnid = this.id;

		if($('#pnl_reserv').length!=0) {
//			alert(this.id);
			$("select#room").val(btnid);
			$("#pnl_reserv").dialog('open');
			return;
		}
			
		$('body').append('<div id="pnl_reserv" />');
		$('#pnl_reserv').load('/reservation/reservation_' + btnclass + '.asp', function() {

			if(btnclass!='staying') {

				$('#chkindate').datepicker({
					dateFormat: "yy-mm-dd",
					minDate: 0
				});
			}
			else {
				$('#chkindate').datepicker({
					dateFormat: "yy-mm-dd",
					minDate: 1
				});
				$('#chkoutdate').datepicker({
					dateFormat: "yy-mm-dd",
					minDate: 2
				});
				$('#chkindate').change(function() {
					$('#chkindate_copy').val($(this).val());
				});
				$('#identical').click(function() {

					var checked = $(this).is(':checked');
					$.each({
						'gphone': $('#rphone').val(),
						'gename': $('#rename').val(),
						'gkname': $('#rkname').val()
					},
					function(k, v) {
						var e = $('#'+k);
						if(checked) {
							e.val(v);
						}
						else if(e.val()==v) {
							e.val('');
							e.focus();
						}
					});
				});
			}

			$('button.btn_close_reserv').click(function(event) {
//				alert('click!');
				event.preventDefault();
				$("#pnl_reserv").dialog('close');
				$("body").css("overflow", "scroll");  // added by publisher
			});

			$('#frm_reserv').validate(validatorOption[btnclass]);
			$('#frm_reserv').submit(function(event) {

				event.preventDefault();
				if(!$('#frm_reserv').valid()) {
					return true;
				}

				var btnsubmit = $('button[type="submit"]', this);
				btnsubmit.prop('disabled', true);
				$.post(this.action, $(this).serializeArray(), function() {
					alert('예약이 완료되었습니다.');
					$("#pnl_reserv").dialog('close');
					btnsubmit.prop('disabled', false);
				});
			});

			$("#pnl_reserv").dialog({
				dialogClass: 'no-titlebar',
				autoOpen: false
//				modal: true
//				position: {my: "left+200, bottom+100", of: "body"},
			});

//			alert(btnid);
			$("select#room").val(btnid);
			$("#pnl_reserv").dialog('open');
		});
	});
});

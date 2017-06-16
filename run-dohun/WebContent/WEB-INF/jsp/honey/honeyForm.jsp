<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.page</title>
</head>

<style type="text/css">
	ul {list-style-type: :none; }
	li {display: inline; }
</style>

<script type="text/javascript">
$(function(){
	//jQgrid  list01 START
	$("#list01").jqGrid({
		url: "/honeyJqgridList.do",
		editurl: "/honeyJqgridMerge.do",	// 셀이 수정될 때 수정 요청을 받아서 처리할 URL
		contentType: "application/json; charset=utf-8",
        datatype: "json",
        jsonReader : {
            page: "page",	// 현재 페이지
            total: "total",	// 총 페이지 수
            root: "result",	// 실제 jqgrid에서 뿌려져야 할 데이터 
            repeatitems: false
         },
		colNames:["회원번호","이름","전화번호","주소","비고"],
	   	colModel:[
	   		{name:"userId",index:"col00",width:100,editable:true,align:"center",editoptions:{readonly:true}},	//no
	   		{name:"userName",index:"col01",width:80,editable:true,align:"center",editrules:{required:true}
	   			,formoptions:{elmsuffix:"	(필수)" },
	   		},		//이름
	   		{name:"userPhone",index:"col02",width:120,editable:true,align:"center",editrules:{required:true}
	   			,formoptions:{elmsuffix:"	(필수)" }
	   			,editoptions: {
	   				dataInit: function (element) {
	   					$(element).keyup(function(){
	   						$(this).val( $(this).val().replace(/[^0-9]/g,"") );
	   					})
	   				}
	   			}
	   		},      //전화번호
	   		{name:"userAddress",index:"col03",width:250,editable:true,editrules:{required:true}
	   			,formoptions:{elmsuffix:"	(필수)" }
	   		},     //주소
	   		{name:"userEtc01",index:"col04",width:250,editable:true}	//비고
	   	],
	   	height: 250,
		width:900,
	   	rowNum: 10,  //한 페이지에 보여줄 데이터 갯수
	   	rowList: [10, 20, 50], //페이징 옵션
     	pager: "#pager01", 
	   	caption: "꿀 장부 화면",
     	loadtext : "로딩중..",
		width:"auto",
     	autowidth:true,	// 그리드 전체 넓이 조정 (오토 조절 가능)
        shrinkToFit: false	//스크롤
	    ,onSelectRow: function(rowid, selected) {
				if(rowid != null) {
					//subGrid 호출
					var rowData = jQuery("#list01").jqGrid("getRowData", rowid);  
					jQuery("#list02").jqGrid("setGridParam",{url:"/honeyJqgridSubList.do",datatype:"json"
						,postData :{
							userId:rowData.userId
						}
					}); 
					jQuery("#list02").jqGrid("setCaption", "꿀 장부 상세화면 -회원번호:"+rowData.userId);
					jQuery("#list02").trigger("reloadGrid");
				}					
			} 
	}).navGrid("#pager01",{edit:true,add:true,del:true,search:false}
		,{}
		,{},//add options
		{mtype:"POST", reloadAfterSubmit:true, serializeDelData: function (postdata) {
		      var rowdata = jQuery("#list01").getRowData(postdata.id);
		      return {index: postdata.id, oper: postdata.oper, userId: rowdata.userId};
		 }
		} // del options
	);
    jQuery("#list01").jqGrid("setFrozenColumns");  // Frozen Columns 칼럼
    jQuery("#list01").trigger("reloadGrid");       // colModel option에 frozen:true 적용해야함
    
  	//jQgrid  list01 END
    
    
    //jQgrid  list02 START
    $("#list02").jqGrid({
		editurl: "/honeyJqgridSubMerge.do?",	// 셀이 수정될 때 수정 요청을 받아서 처리할 URL
		contentType: "application/json; charset=utf-8",
        datatype: "json",
        jsonReader : {
            page: "page",	// 현재 페이지
            total: "total",	// 총 페이지 수
            root: "resultSub",	// 실제 jqgrid에서 뿌려져야 할 데이터 
            repeatitems: false
         },
		colNames:["주문번호","사용자","주문일자","결재여부","주문내역","수량","단가","금액","운송장번호","배송일자"],
	   	colModel:[
	   		{name:"honeyId",index:"col00",width:100,editable:true,align:"center",editoptions:{readonly:true}},		//id
	   		{name:"honeyUserId",index:"col01",hidden:true},		//userId
	   		{name:"honeyOrderDate",index:"col05",width:80,editable:true,align:"center",editrules:{required:true},
	   			editoptions: {
	   				readonly:true,	   				
	   				dataInit: function (element) {
	   					$(element).datepicker({
	   						id: "orderDate_datePicker",
                            dateFormat: "yy-m-d",
                            //minDate: new Date(2010, 0, 1),
                            maxDate: new Date(2020, 0, 1),
                            showOn: 'focus'
                        });
                    }
                }
	   			,formoptions:{elmsuffix:"	(필수)" }
	   		},//주문일자
	   		{name:"honeyPayment",index:"col06",width:50,editable:true,align:"center",								//결재여부
                edittype: "select",                                                                     
                editoptions: {                                                                          
                    value: "미결:미결;결재:결재"                                                                  
                }                                                                                       
	   		},                                                                                          
	   		{name:"honeyOrderList",index:"col07",width:100,editable:true
	   			,edittype: "select"                                                                     
                ,editoptions: {                                                                          
                    value: "잡화:잡화;아카시아:아카시아;꽃화분:꽃화분;프로폴리스:프로폴리스"                                                                  
                }	   			
	   		},//주문내역
	   		{name:"honeyQty",index:"col08",width:100,editable:true,align:"right",editrules:{required:true}
		   		,formatter: "integer"
	   			,formoptions:{elmsuffix:"	(필수)" }
	   			,editoptions: {
	   				dataInit: function (element) {
	   					$(element).keyup(function(){
	   						$(this).val( $(this).val().replace(/[^0-9]/g,"") );
	   					})
	   				}
	   			}
	   		},//수량
	   		{name:"honeyCost",index:"col09",width:100,align:"right",editrules:{required:true},formatter: "integer",
	   			editoptions: {
	   				dataInit: function (element) {
	   					$(element).keyup(function(){
	   						$(this).val( $(this).val().replace(/[^0-9]/g,"") );
	   					})
	   				}
	   			}
	   		},//단가
	   		{name:"honeyAmount",index:"col10",width:100,align:"right",formatter: "integer",
	   			editoptions: {
	   				dataInit: function (element) {
	   					$(element).keyup(function(){
	   						$(this).val( $(this).val().replace(/[^0-9]/g,"") );
	   					})
	   				}
	   			} 
	   		},//금액
	   		{name:"honeyWaybill",index:"col11",width:100,editable:true,align:"center"},//운송장번호
	   		{name:"honeyDeliveryDate",index:"col12",width:80,editable:true,align:"center"
	   			,editoptions: {
	   				readonly:true,	   				
	   				dataInit: function (element) {
	   					$(element).datepicker({
	   						id: 'deliverDate_datePicker',
                            dateFormat: 'yy-m-d',
                            //minDate: new Date(2010, 0, 1),
                            maxDate: new Date(2020, 0, 1),
                            showOn: 'focus'
                        });
                    }
                }
	   		}//배송일자
	   	],
	   	height: 100,
		width:900,
	   	rowNum: 10,  //한 페이지에 보여줄 데이터 갯수
	   	rowList: [10, 20, 50], //페이징 옵션
     	pager: '#pager02', 
	   	caption: "꿀 장부 상세화면",
     	loadtext : '로딩중..',
		width:'auto',
     	autowidth:true,	// 그리드 전체 넓이 조정 (오토 조절 가능)
        shrinkToFit: false	//스크롤
        ,loadComplete: function(data){
        	$("#subUserId").val(data.subUserId);
        }
	}).navGrid("#pager02",{edit:true,add:true,del:true,search:false}
		,{}
		,{mtype:"POST",reloadAfterSubmit:true
			,serializeEditData: function (postdata) {// editGridRow 옵션사용
			return {index:postdata.id
				,oper:postdata.oper
				,honeyUserId: $("#honeyUserId").val()
				,honeyOrderDate: $("#honeyOrderDate").val()
				,honeyPayment: $("#honeyPayment").val()  
				,honeyOrderList: $("#honeyOrderList").val()
				,honeyQty: $("#honeyQty").val()
				,honeyCost: $("#honeyCost").val()
				,honeyAmount: $("#honeyAmount").val()
				,honeyWaybill: $("#honeyWaybill").val()
				,honeyDeliveryDate: $("#honeyDeliveryDate").val()
				,subUserId:$("#subUserId").val()};
				} 
		}// add options
		,{mtype:"POST",reloadAfterSubmit:true,serializeDelData: function (postdata) {
			var rowdata = jQuery('#list02').getRowData(postdata.id);
		      return {
		    	  index:postdata.id 
		    	  ,oper:postdata.oper
		    	  ,honeyId:rowdata.honeyId
		      };}
		}// del options
	);
    jQuery("#list02").trigger('reloadGrid');       // colModel option에 frozen:true 적용해야함
  	//jQgrid  list02 END

});
    
</script>

<form id="honeyForm"  method="post" >
<input type="hidden" id="subUserId"/>
    <div class="col-sm-8 text-left">
		<h1>Honey</h1>
		<p>나는 꿀을판다.</p>
		<hr>
		<h3>후니허니</h3>
		<p>나는야 꿀을파는청년...</p>
		<div>
			<table id="list01"></table>
			<div id="pager01"></div>
		</div>
		<div>
			<table id="list02"></table>
			<div id="pager02"></div>
		</div>
		
    </div>
</form>
</html>
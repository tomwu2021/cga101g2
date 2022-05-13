/**
 * 查詢日期
 */
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:true,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
           value :					'2022/04/01',  // 起始日
           startDate:	            '2022/04/01',  // 起始日
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:true,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
           value: new Date(), 		//今天
           maxDate: new Date()  
        });
        
        
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:true,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
//  	       value :					'2022/04/01',  // 起始日
           minDate: '2022/04/01', // 起始日
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date4').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:true,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
//           value: new Date(), 		//今天
           maxDate: new Date(), 
        });
       
        function getNewDate(){
    	 return new Date();;
		}
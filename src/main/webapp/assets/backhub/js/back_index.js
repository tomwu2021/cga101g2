function cancel(){
  Swal.fire({
  title: '您確定嗎？',
  text: '訂單將無法復原！',
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  cancelButtonText: '返回',
  confirmButtonText: '取消'
}).then((result) => {
  if (result.isConfirmed) {
    Swal.fire(
      '訂單已取消',
      '期待您再次光顧',
      'success'
    )
  }
})};

//! 改變&復原左側side hover顏色
function hover_color(x){
x.style.cssText='background-color:#4680fe;'
}
function default_color(x){
x.style.cssText='background-color:#343a40;'
}
//! 改變&復原side hover顏色 結束
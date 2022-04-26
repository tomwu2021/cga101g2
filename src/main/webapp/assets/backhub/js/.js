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

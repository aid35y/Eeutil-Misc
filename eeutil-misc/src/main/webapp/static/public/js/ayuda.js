function showAyudaDialog() {

    $('#init-modal-ayuda').removeClass('hidden');

    var divData = $('#init-modal-ayuda');
    var dataModal = divData.data();

    dataModal.data = divData.html();

    $mf.my_dialog.appendDialog(dataModal, true, true);

}
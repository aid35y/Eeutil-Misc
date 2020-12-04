$(document).ready(
        function() {

            tableAplicaciones = $('#capturarPeticionesList').dataTable({
                "language" : {
                    "sProcessing" : "Procesando...",
                    "sLengthMenu" : "Mostrar _MENU_",
                    "sZeroRecords" : "No se encontraron resultados",
                    "sEmptyTable" : "Ningún dato disponible en esta tabla",
                    "sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                    "sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
                    "sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
                    "sInfoPostFix" : "",
                    "sSearch" : "Buscar",
                    "sUrl" : "",
                    "sInfoThousands" : ",",
                    "sLoadingRecords" : "Cargando...",
                    "oPaginate" : {
                        "sFirst" : "Primero",
                        "sLast" : "Último",
                        "sNext" : "Siguiente",
                        "sPrevious" : "Anterior"
                    },
                    "oAria" : {
                        "sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
                        "sSortDescending" : ": Activar para ordenar la columna de manera descendente"
                    }
                },
                "lengthMenu" : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "Todo" ] ],
                "pageLength" : 25,
                "dom" : '<"top fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
                "bSort" : true,
                "aoColumns" : [ {
                    "bSortable" : false,
                    "width" : "30%"
                }, {
                    "bSortable" : false,
                    "width" : "30%"
                }, {
                    "bSortable" : false,
                    "width" : "10%"
                }, {
                    "bSortable" : false,
                    "width" : "30%"
                } ],
                "paging" : true
            });

            tableCapturas = $('#peticionesList').DataTable(
                    {
                        "language" : {
                            "sProcessing" : "Procesando...",
                            "sLengthMenu" : "Mostrar _MENU_",
                            "sZeroRecords" : "No se encontraron resultados",
                            "sEmptyTable" : "Ningún dato disponible en esta tabla",
                            "sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                            "sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
                            "sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
                            "sInfoPostFix" : "",
                            "sSearch" : "Buscar",
                            "sUrl" : "",
                            "sInfoThousands" : ",",
                            "sLoadingRecords" : "Cargando...",
                            "oPaginate" : {
                                "sFirst" : "Primero",
                                "sLast" : "Último",
                                "sNext" : "Siguiente",
                                "sPrevious" : "Anterior"
                            },
                            "oAria" : {
                                "sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
                                "sSortDescending" : ": Activar para ordenar la columna de manera descendente"
                            }
                        },
                        "lengthMenu" : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "Todo" ] ],
                        "pageLength" : -1,
                        "dom" : '<"top fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
                        "bSort" : true,
                        "bFilter" : false,
                        "columns" : [
                                {
                                    "data" : "aplicacion"
                                },
                                {
                                    "data" : "operacion"
                                },
                                {
                                    "data" : "fechaString",
                                    className : "dt-body-center"
                                },
                                {
                                    "render" : function(data, type, row) {
                                        return "<a href='#!' onclick='descargarPeticion(\"" + row.aplicacion + "\",\""
                                                + row.operacion + "\",\"" + row.fecha + "\")' title='Descargar'>"
                                                + "<span class='mf-icon mf-icon-doc-download'></span>"
                                                + "<span th:text='Descargar'></span>" + "</a>";
                                    }
                                } ],
                        "paging" : false
                    });

        });

function formatDate(date) {
    var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" ];

    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();

    return day + ' ' + monthNames[monthIndex] + ' ' + year;
}

function showDialogEdit(aplicacion, operacion, activar) {
    if (activar == "true") {
        $("#labelModificar").text($("#labelDesactivar").val());
    } else {
        $("#labelModificar").text($("#labelActivar").val());
    }

    var divData = $('#init-modal-modificar-captura');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-borrar',
        texto : 'Confirmar'
    } ]);

    buttons.find('.js-documento-borrar').on('click', function(e) {
        var capturar = activar == "true" ? false : true;
        moficarCaptura(aplicacion, operacion, capturar);
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;

    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
}

function createButtonsDialog(buttons) {
    var ret = $('<ul class="mf-buttonbar">');
    $(buttons).each(
            function(k, v) {
                $(
                        '<li class="mf-buttonbar--item"><button ' + (v.submit ? 'type="submit"' : '')
                                + ' class="buttonbar--btn ' + v.clase + '" href="#!">' + v.texto + '</a></li>')
                        .appendTo(ret);
            });
    return ret;
}

function moficarCaptura(aplicacion, operacion, capturar) {
    $("#modificarCapturaForm #aplicacion").val(aplicacion);
    $("#modificarCapturaForm #operacion").val(operacion);
    $("#modificarCapturaForm #capturar").val(capturar);
    $("#modificarCapturaForm").submit();
}

function showDialogDelete(aplicacion, operacion) {
    if (aplicacion == '' && operacion == '') {
        $("#labelEliminar").text($("#laberEliminarTodas").val());
    } else {
        $("#labelEliminar").text($("#laberEliminarSimple").val());
    }
    var divData = $('#init-modal-eliminar-captura');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-borrar',
        texto : 'Confirmar'
    } ]);

    buttons.find('.js-documento-borrar').on('click', function(e) {
        eliminarCaptura(aplicacion, operacion);
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;

    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
}

function eliminarCaptura(aplicacion, operacion) {
    if (aplicacion == '' && operacion == '') {
        $("#eliminarTodasPeticionesForm").submit();
    } else {
        $("#eliminarPeticionForm #aplicacion").val(aplicacion);
        $("#eliminarPeticionForm #operacion").val(operacion);
        $("#eliminarPeticionForm").submit();
    }
}

function showDialogFind(aplicacion, operacion) {
    tableCapturas.clear();
    tableCapturas.ajax.url("consultarPeticiones?aplicacion=" + aplicacion + "&operacion=" + operacion).load();

    setTimeout(function() {
        var divData = $('#init-modal-find-capturas');
        var data = divData.data();

        var buttons = createButtonsDialog([ {
            clase : 'js-documento-cancelar',
            texto : 'Cancelar'
        } ]);

        buttons.find('.js-documento-cancelar').on('click', function(e) {
            $mf.my_dialog.close_dialog();
        });

        data.buttons = buttons;

        data.data = divData.html();
        $mf.my_dialog.appendDialog(data, true, true);
    }, 1000);
}

function descargarPeticion(aplicacion, operacion, fecha) {
    $("#descargarPeticionForm #aplicacion").val(aplicacion);
    $("#descargarPeticionForm #operacion").val(operacion);
    $("#descargarPeticionForm #fecha").val(fecha);
    $("#descargarPeticionForm").submit();
}
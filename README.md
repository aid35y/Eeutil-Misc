# eeutil-misc

Instalación y evolutivo de la versión distribuible de Eeutil-Misc.

Se parte de la versión distribuible que se ofrece en el Área de descargas de la Suite Inside del Centro de Transferencia Tecnológica:
*  https://administracionelectronica.gob.es/ctt/inside/descargas -> Versión distribuible (Código fuente) -> Distribuible Eeutil-Misc v4.2.0 (noviembre 2018)

Eeutils(CSV Creator) es el componente de la Suite CSV que agrupa diversas funcionalidades relacionadas con la generación de CSV y gestión de firmas e informes. Eeutils(CSV Creator) se divide en cinco módulos, entre los que se encuentra Eeutil-Misc.

**Eeutil-Misc** es el componente utilizado para la validación ENI de documentos y expedientes.

# Características a tener en cuenta

En este proyecto se utilizan jobs o disparadores que se intentan conectar a la base de datos para obtener las unidades orgánicas y las oficinas. Concretamente, se hace referencia al job que se encuentra en el fichero **quartz.xml** de la ruta *fuentes/eeutil-misc/src/main/resources/es/mpt/dsic/inside/context*

En este fichero, se realiza una llamada a *unidadOrganicaTrigger* que empieza pasados los 10 segundos a través de la propiedad *${ws.unidadesOrganicas.startDelay}* y se repite cada día a través de la propiedad *${ws.unidadesOrganicas.repeatInterval}*

La clase que se consulta es *es.mpt.dsic.loadTables.controller.UnidadOrganicaController* del proyecto *eeutil-shared/fuentes/load-tables/src/main/java/es/mpt/dsic/loadTables/controller* , donde se consultan las unidades orgánicas a través del método **loadUnidadOrganica** y las oficinas correspondientes a través del método **loadOficinas**.

Para poder ejecutar el WAR en local, es necesario configurar el archivo *database.properties* que se encuentra en *resources/config* con lo correspondiente a la base de datos que se quiera usar. Indicar que en la propiedad **databaseGen.url** habría que indicar la URL con el formato similar al siguiente:



    jdbc:mysql://localhost:3306/eeutils?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

Además, es necesario agregar el JAR de MySQL en la carpeta *lib* del Tomcat.

### Operaciones de eeutil-misc
Este proyecto se compone de varios servicios, los cuales no se utilizan desde **INSIDE**. En este caso, son los siguientes:

- **EeUtilService**
- **EeUtilServiceMtom**
- **EeutilValidacionENIService** : Las operaciones son las mismas que las del servicio **EeUtilValidacionENIServiceMtom** ya que se llaman desde ese servicio

Los servicios de eeutil-misc que se utilizan en **INSIDE** son:

**1**.  **EeUtilValidacionENIServiceMtom** : Este servicio se compone de las siguientes operaciones:
- **validarFirmaExpedienteENI** : Se utiliza el servicio de **INSIDE** **/validaExpediente** a través del método *ConsumidorValidacionENI- scMtom.validarFirmaExpedienteENI*

- **validarFirmaDocumentoENI** :  Se utiliza el servicio de **INSIDE** **/validarDocumento** a través del método * ConsumidorValidacionENI- scMtom.validarFirmaDocumentoENI*


# AgenciesApi
Projecto con una API que extiende la API de agencias  https://agencies.docs.apiary.io/#introduction/get-nearest-agencies-by-geopoint y por consola permite ejecutar los métodos desde consola

## Modo de uso
Para utilizar la api se debe hacer una llamada HTTP GET al endpoint /agencias y especificar por ruta los siguientes parametros:
```
{SITE_ID}
(Requerido) Identificador de pagina regional de Mercado Libre. 
Ej: MLA

{METODO_PAGO}
(Requerido) Identificador de método de pago presencial payment_type_id=ticket. 
Ej: rapipago

{LAT,LONG,RADIO}
(Opcional) Latitud, Logintud y Radio en metros de búsqueda. 
Ej: -31.412971,-64.18758,300

{LIMITE}
(Opcional) Limitar cantidad de resultados. 
Ej: 10

{OFFSET}
(Opcional) El número del primer item a devolver. 
Ej: 2

{CAMPO}
(Opcional) Campo por el cual vendrán ordenados los resultados, puede ser DISTANCE por defecto, 
ADDRESS_LINE o AGENCY_CODE
```
```
GET http://localhost:4567/agencias?site_id={SITE_ID}&payment_method_id={METODO_PAGO}&near_to={LAT,LONG,RADIO}&limit={LIMITE}&offset={OFFSET}&order_by={CAMPO}
```

# rest-coreografia-medicos-fixed

curls:

Consultar historial paciente:
curl --location 'http://localhost:8080/V1/api/historial/1'

Crear historial para paciente:
curl --location 'http://localhost:8080/V1/api/historial/4' \
--header 'Content-Type: application/json' \
--data '{
"diagnostico": "Alergia estacional",
"fecha": "2024-11-01"
}'


Consultar laboratorio paciente:
curl --location 'http://localhost:8080/V1/api/laboratorio/1'

crear laboratorio para paciente:
curl --location 'http://localhost:8080/V1/api/laboratorio/2' \
--header 'Content-Type: application/json' \
--data '{
"tipo": "Rayos X",
"resultado": "Fractura detectada",
"fecha": "2024-11-01"
}'


servidor:
https://rest-coreografia-medicos-fixed.onrender.com

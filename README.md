1. Use this curl request on local for updating the already created document in the inputFile folder. 

curl --location --request GET 'http://localhost:8080/fill-pdf?pdfPath=%2FUsers%2FuSER_NAME%2FTamara%2Fdemo-pdf-document%2Finputfile%2Fsample.pdf&outputPath=%2FUsers%2FuSER_NAME%2FTamara%2Fdemo-pdf-document%2Foutputfile%2Fsample.pdf' \
--header 'Content-Type: application/json' \
--data '{
    "lastName": "test",
    "orderAmount": "25003"
}'

2. Replace the user_name with your login in username of your device
3. out file will be store in the folder outputfile

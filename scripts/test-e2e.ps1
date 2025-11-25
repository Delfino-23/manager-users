# Script de teste E2E para a API com JWT
# Requisitos: Docker (opcional), curl (ou Invoke-RestMethod), PowerShell

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
Write-Host "Projeto: $projectRoot"

Write-Host "1) Subir MySQL via docker-compose (se desejar)"
docker compose up -d

Write-Host "Aguardando container e aplicação (10s)..."
Start-Sleep -Seconds 10

# cria admin
$adminJson = '{"name":"Admin Test","email":"admin.test@example.com","password":"admTest123","phone":"11970000000","cpf":"00011122233"}'
Write-Host "2) Criando administrador..."
Invoke-RestMethod -Method Post -Uri http://localhost:8080/administrators -Body $adminJson -ContentType 'application/json' | ConvertTo-Json

# cria user
$userJson = '{"name":"User Test","email":"user.test@example.com","password":"userTest123","phone":"11999990000","cpf":"11122233344","course":"Test Course"}'
Write-Host "3) Criando usuário..."
Invoke-RestMethod -Method Post -Uri http://localhost:8080/users -Body $userJson -ContentType 'application/json' | ConvertTo-Json

Start-Sleep -Seconds 2

Write-Host "4) Autenticando como admin para obter JWT..."
$loginJson = '{"email":"admin.test@example.com","password":"admTest123"}'
$resp = Invoke-RestMethod -Method Post -Uri http://localhost:8080/auth/login -Body $loginJson -ContentType 'application/json'
Write-Host "Token recebido: $($resp.token)"

Write-Host "5) Chamando endpoint protegido /users/ com token"
$headers = @{ Authorization = "Bearer $($resp.token)" }
Invoke-RestMethod -Method Get -Uri http://localhost:8080/users/ -Headers $headers | ConvertTo-Json

Write-Host "Teste concluído. Se deu erro 401/403, verifique logs da aplicação e se os registros existem no DB."

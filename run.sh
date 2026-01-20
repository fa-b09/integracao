#!/bin/bash

# 1. Compilar o COBOL
echo "🔨 Compilando COBOL..."
cobc -x -free -o cobol/calculo_bin cobol/calculo.cbl

# 2. Compilar o Java
echo "🔨 Compilando Java..."
javac App.java

# 3. Executar o Programa
if [ $? -eq 0 ]; then
    echo "🚀 Iniciando Aplicação..."
    java App
else
    echo "❌ Erro na compilação!"
fi
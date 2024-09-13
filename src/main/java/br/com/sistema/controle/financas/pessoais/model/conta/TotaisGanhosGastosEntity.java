package br.com.sistema.controle.financas.pessoais.model.conta;

public class TotaisGanhosGastosEntity {

    private double totalGanho;
    private double totalGastos;
    public TotaisGanhosGastosEntity(double totalGanho, double totalGastos) {
        this.totalGanho = totalGanho;
        this.totalGastos = totalGastos;
    }
    public double getTotalGanho() {
        return totalGanho;
    }

    public void setTotalGanho(double totalGanho) {
        this.totalGanho = totalGanho;
    }

    public double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(double totalGastos) {
        this.totalGastos = totalGastos;
    }
}

package com.tecmilenio.imctracker.util;
public final class IMCCalculadora {
  private IMCCalculadora(){}
  public static double calcularIMC(double pesoKg, double estaturaM){
    if(estaturaM <= 0) throw new IllegalArgumentException("Estatura inválida");
    return Math.round((pesoKg/(estaturaM*estaturaM))*100.0)/100.0;
  }
  public static String clasificar(double imc){
    if (imc < 18.5) return "Bajo peso";
    if (imc < 25) return "Normal";
    if (imc < 30) return "Sobrepeso";
    return "Obesidad";
  }
}

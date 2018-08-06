package br.com.ufersa.arlan.gasp.beans;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Servico implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String produto;
    private String defeito;
    private long dataEntrada;
    private long dataSaida;
    private String status;
    private String descricao;
    private double valor;
    private String clienteNome;
    private String clienteCPF;
    private String prestadorNome;
    private String prestadorCNPJ;

    public Servico(){};


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public long getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(long dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public long getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(long dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String cliente) {
        this.clienteNome = cliente;
    }

    public String getPrestadorNome() {
        return prestadorNome;
    }

    public void setPrestadorNome(String prestador) {
        this.prestadorNome = prestador;
    }

    public String getClienteCPF() {
        return clienteCPF;
    }

    public void setClienteCPF(String clienteCPF) {
        this.clienteCPF = clienteCPF;
    }

    public String getPrestadorCNPJ() {
        return prestadorCNPJ;
    }

    public void setPrestadorCNPJ(String prestadorCNPJ) {
        this.prestadorCNPJ = prestadorCNPJ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(produto);
        dest.writeString(defeito);
        dest.writeLong(dataEntrada);
        dest.writeLong(dataSaida);
        dest.writeString(status);
        dest.writeString(descricao);
        dest.writeDouble(valor);
        dest.writeString(clienteNome);
        dest.writeString(clienteCPF);
        dest.writeString(prestadorNome);
        dest.writeString(prestadorCNPJ);

    }

    protected Servico(Parcel in) {
        id = in.readInt();
        produto = in.readString();
        defeito = in.readString();
        dataEntrada = in.readLong();
        dataSaida = in.readLong();
        status = in.readString();
        descricao = in.readString();
        valor = in.readDouble();
        clienteNome = in.readString();
        clienteCPF = in.readString();
        prestadorNome = in.readString();
        prestadorCNPJ = in.readString();
    }

    public static final Creator<Servico> CREATOR = new Creator<Servico>() {
        @Override
        public Servico createFromParcel(Parcel in) {
            return new Servico(in);
        }

        @Override
        public Servico[] newArray(int size) {
            return new Servico[size];
        }
    };
}

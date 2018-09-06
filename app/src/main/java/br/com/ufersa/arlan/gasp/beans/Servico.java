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

    // construtor para o room
    public Servico(String produto, String defeito, long dataEntrada, long dataSaida, String status, String descricao, double valor, String clienteNome, String clienteCPF, String prestadorNome, String prestadorCNPJ) {
        this.produto = produto;
        this.defeito = defeito;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.status = status;
        this.descricao = descricao;
        this.valor = valor;
        this.clienteNome = clienteNome;
        this.clienteCPF = clienteCPF;
        this.prestadorNome = prestadorNome;
        this.prestadorCNPJ = prestadorCNPJ;
    }

    private Servico(final ServicoBuilder builder) {
        produto = builder.produto;
        defeito = builder.defeito;
        dataEntrada = builder.dataEntrada;
        dataSaida = builder.dataSaida;
        status = builder.status;
        descricao = builder.descricao;
        valor = builder.valor;
        clienteNome = builder.clienteNome;
        clienteCPF = builder.clienteCPF;
        prestadorNome = builder.prestadorNome;
        prestadorCNPJ = builder.prestadorCNPJ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public String getDefeito() {
        return defeito;
    }

    public long getDataEntrada() {
        return dataEntrada;
    }

    public long getDataSaida() {
        return dataSaida;
    }

    public String getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getClienteCPF() {
        return clienteCPF;
    }

    public String getPrestadorNome() {
        return prestadorNome;
    }

    public String getPrestadorCNPJ() {
        return prestadorCNPJ;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public void setDataEntrada(long dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(long dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public void setClienteCPF(String clienteCPF) {
        this.clienteCPF = clienteCPF;
    }

    public void setPrestadorNome(String prestadorNome) {
        this.prestadorNome = prestadorNome;
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

    public static class ServicoBuilder {
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

        public ServicoBuilder setProduto(String produto) {
            this.produto = produto;
            return this;
        }

        public ServicoBuilder setDefeito(String defeito) {
            this.defeito = defeito;
            return this;
        }

        public ServicoBuilder setDataEntrada(long dataEntrada) {
            this.dataEntrada = dataEntrada;
            return this;
        }

        public ServicoBuilder setDataSaida(long dataSaida) {
            this.dataSaida = dataSaida;
            return this;
        }

        public ServicoBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ServicoBuilder setDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public ServicoBuilder setValor(double valor) {
            this.valor = valor;
            return this;
        }

        public ServicoBuilder setClienteNome(String clienteNome) {
            this.clienteNome = clienteNome;
            return this;
        }

        public ServicoBuilder setClienteCPF(String clienteCPF) {
            this.clienteCPF = clienteCPF;
            return this;
        }

        public ServicoBuilder setPrestadorNome(String prestadorNome) {
            this.prestadorNome = prestadorNome;
            return this;
        }

        public ServicoBuilder setPrestadorCNPJ(String prestadorCNPJ) {
            this.prestadorCNPJ = prestadorCNPJ;
            return this;
        }

        public Servico createServico() {
            return new Servico(this);
        }
    }
}

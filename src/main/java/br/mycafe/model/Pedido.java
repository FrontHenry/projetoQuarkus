package br.mycafe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pedido")
public class Pedido extends PanacheEntityBase {
    @Column()
    @SequenceGenerator(name = "pedidoIdSequence", sequenceName = "pedido_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pedidoIdSequence")
    @Id
    public Long id;

    @ManyToOne()
    @JsonIgnoreProperties("pedido")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "idCliente")
    public Cliente cliente;
    @ManyToOne()
    @JsonIgnoreProperties("pedido")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    public Usuario usuario;

    @ManyToOne()
    @JsonIgnoreProperties("pedido")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "idUsuario")
    public Usuario usuarioAlt;

    @Column()
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date dataCriacao;

    @Column()
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date dataAlt;

    @Column()
    public boolean excluido;

    @Column()
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date dataExclusao;

    public Pedido() {

    }

}

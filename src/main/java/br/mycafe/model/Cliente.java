package br.mycafe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends PanacheEntityBase {

    @Column()
    @SequenceGenerator(name = "clienteIdSequence", sequenceName = "cliente_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "clienteIdSequence")
    @Id
    public Long id;

    @Column()
    public String nome;

    @Column()
    public String email;

    @Column()
    public Long celular;

    @Column()
    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date dataNascimento;

    @Column()
    public String endereco;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Pedido.class, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    List<Pedido> pedidoList;

    @ManyToOne()
    @JsonIgnoreProperties("cliente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    public Usuario usuario;

    @ManyToOne()
    @JsonIgnoreProperties("cliente")
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

    public Cliente() {

    }
}

package br.mycafe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@UserDefinition
public class Usuario extends PanacheEntityBase {
    @Column()
    @SequenceGenerator(name = "usuarioIdSequence", sequenceName = "usuario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usuarioIdSequence")
    @Id
    public Long id;

    @Column(nullable = false)
    @Username
    public String login;

    @Column(nullable = false, length = 10)
    @Password
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    @Column()
    public String nome;

    @Column()
    public String email;

    @Column()
    @Roles
    public String permissao;

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Cliente.class, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    List<Cliente> clienteList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Pedido.class, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    List<Pedido> pedidoList;

    public Usuario() {

    }

}

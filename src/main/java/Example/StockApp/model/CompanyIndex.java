package Example.StockApp.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "company_index")
@Getter
@Setter
public class CompanyIndex {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "stock_prefix")
    private String stockPrefix;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;





}

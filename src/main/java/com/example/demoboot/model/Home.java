package com.example.demoboot.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Home {
    //@All Mini test:
    //Thông tin nhà bao gồm:
    //- Tên của căn nhà (Không được để trống)
    //- Địa chỉ (Không được để trống)
    //- Số lượng phòng ngủ () :1-10 phòng
    //- Số lượng phòng tắm (): 1-3 phòng
    //- Giá tiền theo ngày(VNĐ)()
    //- Ảnh: nếu không đăng thì có ảnh mặc định . Chỉ cho chọn file jpeg, png.
    //Các trường phải validate.
    //Làm các chức năng (API, Ajax) (Giao diện dùng bootstrap):
    //- Thêm nhà
    //- Hiểm thị danh sách nhà
    //Nâng cao: phân trang.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Không để trống tên!")
    private String name;
    @NotBlank(message = "Không để trống địa chỉ!")
    private String address;
    @NotNull(message = "Không để trống số phòng ngủ!")
    @Range(min = 1, max = 10, message = "Số phòng ngủ từ 1 đến 10!")
    private int numberOfBathroom;
    @NotNull(message = "Không để trống số phòng tắm!")
    @Range(min = 1, max = 3, message = "Số phòng tắm từ 1 đến 3!")
    private int numberOfBedroom;
    @NotNull(message = "Không để trống giá tiền!")
    private int price;
    @Pattern(regexp = ".*((.png)|(.jpg))" , message = "Chỉ được upfile png hoặc jpg")
    private String image;

    public Home() {
    }

    public Home(Long id, String name, String address, int noBathroom, int noBedroom, int price, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfBathroom = noBathroom;
        this.numberOfBedroom = noBedroom;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfBathroom() {
        return numberOfBathroom;
    }

    public void setNumberOfBathroom(int noBathroom) {
        this.numberOfBathroom = noBathroom;
    }

    public int getNumberOfBedroom() {
        return numberOfBedroom;
    }

    public void setNumberOfBedroom(int noBedroom) {
        this.numberOfBedroom = noBedroom;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

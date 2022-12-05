package elasticsearch.demo.service;

import elasticsearch.demo.document.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class FakeDataService {

    private static final Logger LOG = LoggerFactory.getLogger(FakeDataService.class);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final VehicleService vehicleService;
    private final LocationService locationService;

    @Autowired
    public FakeDataService(VehicleService vehicleService, LocationService locationService) {
        this.vehicleService = vehicleService;
        this.locationService = locationService;
    }

//    public void insertDummyData() {
//        vehicleService.index(buildVehicle("1", "Audi A1", "AAA-123", "2010-01-01", true, 10,"Khi đã đề cập mối quan hệ của bất kì yếu tố nào trong xã hội (con người với con người, con người với tự nhiên, con người với tổ chức, các tổ chức với nhau...)"));
//        vehicleService.index(buildVehicle("2", "Audi A3", "AAB-123", "2011-07-05", false, 33,"trước hết cần nhận rõ những yếu tố có tính độc lập của từng yếu tố khách quan về: chức năng, nguồn gốc, xuất xứ, cơ chế tác động, mục đích tổ chức (chỉ có trong các yếu tố chủ quan), cơ sở hình thành mối quan hệ..."));
//        vehicleService.index(buildVehicle("3", "Audi A3", "AAC-123", "2012-10-03", true, 22,"Quan hệ giữa Đảng Cộng sản Việt Nam và nhà nước ở Việt Nam thuộc loại quan hệ giữa đảng chính trị với bộ máy công quyền."));
//
//        vehicleService.index(buildVehicle("4", "BMW M3", "AAA-023", "2021-10-06", true, 13,"Đảng là tổ chức của một lực lượng trong một quốc gia (với một số người trong dân cư). Hiện chưa có đảng chính trị nào mà toàn bộ dân cư nước đó đều là đảng viên của đảng đó cả. Và cũng chưa có công trình nào nghiên cứu, tác giả nào dự báo rằng có thể xảy ra tình trạng là có bao nhiêu dân cư trong một nước thì bấy nhiêu người đều là đảng viên của một đảng chính trị. "));
//        vehicleService.index(buildVehicle("5", "BMW 3", "1AA-023", "2001-10-01", false, 34,"Theo chỗ tôi biết, Đảng Cộng sản Việt Nam cũng không có tham vọng như thế trong khi rất có nhu cầu và mở rộng điều kiện, sao cho có ngày càng nhiều người muốn tham gia trong hàng ngũ của Đảng. Tôi muốn nhấn mạnh chỗ này đã thấy sự khác biệt của hai ''cá thể'' nào trong đó:"));
//        vehicleService.index(buildVehicle("6", "BMW M5", "12A-023", "1999-05-08", true, 54,"chính trị thuần túy (đảng) và công quyền đích thực (nhà nước)."));
//
//        vehicleService.index(buildVehicle("7", "VW Golf", "42A-023", "1991-04-08", true, 12,"Trong quá trình ra đời, hình thành và phát triển, quan điểm của Đảng cũng đã nhấn mạnh sự khác biệt này ở nhiều giác độ khác nhau. Trong văn kiện của Đại hội Đảng lần thứ XI vừa qua không thiếu những chỗ, những đoạn nêu ra và quán triệt trong Đảng bằng biểu quyết và nhất trí cao: Đảng cầm quyền, còn nhà nước quản lý (trực tiếp); Đảng không làm thay nhà nước..."));
//        vehicleService.index(buildVehicle("8", "VW Passat", "18A-023", "2021-04-08", false, 1,"Hơn nữa nếu còn có sự chưa phân định rõ, minh bạch trong mối quan hệ đương nhiên là vẫn còn. Mà đã còn thì sẽ chưa khoa học về thể chế,"));
//
//        vehicleService.index(buildVehicle("9", "Skoda Kodiaq", "28A-023", "2020-01-04", true, 123,"chưa minh định được chức năng của mỗi chủ thể. Hậu quả xã hội (kinh tế, chính trị, xã hội, quốc tế ...) đương nhiên sẽ xuất hiện và cần phải nhận thức và khắc phục. Ví dụ như văn kiện Đảng đã chỉ rõ được là ''...Công tác nghiên cứu lý luận, tổng kết thực tiễn chưa làm sáng tỏ được một số vấn đề về Đảng cầm quyền”1."));
//        vehicleService.index(buildVehicle("10", "Skoda Yeti", "88A-023", "2015-03-09", true, 3543,"Vậy ai nghiên cứu và ai sử dụng các công trình nghiên cứu? Theo tôi người sử dụng và người nghiên cứu đều rõ cả. Người sử dụng chủ yếu Đảng và nhà nước (cũng là những tinh hoa trong Đảng). Người nghiên cứu thì đương nhiên là các trí thức. Mà tri thức nước ta hiện nay đa số đều là đảng viên cả."));
//        vehicleService.index(buildVehicle("11", "AAA", "88B-123", "2015-03-03", true, 63,"Có điều họ là trí thức thì chức năng là tạo ra các sản phẩm khoa học. Nhưng tình trạng cần phải khắc phục là sự ''bao biện làm thay” của Đảng trong công việc điều hành của nhà nước (cá nhân và tổ chức trong Đảng) thì rõ ràng người khắc phục là Đảng và đảng viên"));
//
//        vehicleService.index(buildVehicle("12", "Tu Nguyen", "88B-123", "2015-03-03", true, 63,"Có điều họ là trí thức thì chức năng là tạo ra các sản phẩm khoa học. Nhưng tình trạng cần phải khắc phục là sự ''bao biện làm thay” của Đảng trong công việc điều hành của nhà nước (cá nhân và tổ chức trong Đảng) thì rõ ràng người khắc phục là Đảng và đảng viên"));
//        vehicleService.index(buildVehicle("13", "Tú Nguyễn", "88B-123", "2015-03-03", true, 63,"Có điều họ là trí thức thì chức năng là tạo ra các sản phẩm khoa học. Nhưng tình trạng cần phải khắc phục là sự ''bao biện làm thay” của Đảng trong công việc điều hành của nhà nước (cá nhân và tổ chức trong Đảng) thì rõ ràng người khắc phục là Đảng và đảng viên"));
//
//        vehicleService.index(buildVehicle("14", "Tú Nguyễn Văn", "88B-123", "2015-03-03", true, 63,"Có điều họ là trí thức thì chức năng là tạo ra các sản phẩm khoa học. Nhưng tình trạng cần phải khắc phục là sự ''bao biện làm thay” của Đảng trong công việc điều hành của nhà nước (cá nhân và tổ chức trong Đảng) thì rõ ràng người khắc phục là Đảng và đảng viên"));
//
//
//        vehicleService.index(buildVehicle("6", "Tú Nguyễn", "12A-023", "1999-05-08", true, 54,"chính trị thuần túy (đảng) và công quyền đích thực (nhà nước)."));
//
//    }

//    private static CellProcessor[] getProcessors() {
//        final CellProcessor[] processors = new CellProcessor[] {
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(new ParseInt()),
//                new Optional(new ParseInt()),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(new ParseInt()),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(),
//                new Optional(new ParseInt()),
//        };
//        return processors;
//    }
//
//    public void insertFakeData() {
//        String path =  "C:\\Users\\admin\\Downloads\\final.csv";
//
//        try(ICsvBeanReader beanReader
//                    = new CsvBeanReader(new FileReader(path), CsvPreference.STANDARD_PREFERENCE))
//        {
//            final String[] headers = beanReader.getHeader(true);
//            final CellProcessor[] processors = getProcessors();
//
//            db_location_5 db;
//            while ((db = beanReader.read(db_location_5.class, headers, processors)) != null) {
//                locationService.index(db);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static db_location_5 builddb_location_5(
//             String version,
//             String wardname,
//             String markername,
//             String adddetailen,
//             String districtname,
//             String openhouse,
//             String shortname,
//             String province_area_code,
//             String _category,
//             String area,
//             int id,
//             int priority,
//             String address,
//             String adddetailvn,
//             String phone,
//             String type,
//             String commune_area_code,
//             String name,
//             String email,
//             String nameen,
//             String postalcode,
//             String housenumaddress,
//             String timestamp,
//             String streetaddress,
//             String imagepath,
//             int poitype,
//             String provincename,
//             String location,
//             String obj_id,
//             String district_area_code,
//             String addressen,
//             String countryname,
//             String gid,
//             int thearea
//    ) {
//        db_location_5 db = new db_location_5();
//        db.set_category(_category);
//        db.setAdddetailen(adddetailen);
//        db.setAdddetailvn(adddetailvn);
//        db.setAddress(address);
//        db.setAddressen(addressen);
//        db.setArea(area);
//        db.setCommune_area_code(commune_area_code);
//        db.setCountryname(countryname);
//        db.setDistrict_area_code(district_area_code);
//        db.setDistrictname(districtname);
//        db.setEmail(email);
//        db.setGid(gid);
//        db.setHousenumaddress(housenumaddress);
//        db.setId(id);
//        db.setImagepath(imagepath);
//        db.setLocation(location);
//        db.setMarkername(markername);
//        db.setName(name);
//        db.setNameen(nameen);
//        db.setObj_id(obj_id);
//        db.setOpenhouse(openhouse);
//        db.setPhone(phone);
//        db.setPoitype(poitype);
//        db.setPostalcode(postalcode);
//        db.setPriority(priority);
//        db.setProvince_area_code(province_area_code);
//        db.setProvincename(provincename);
//        db.setStreetaddress(streetaddress);
//        db.setShortname(shortname);
//        db.setThearea(thearea);
//        db.setWardname(wardname);
//        db.setVersion(version);
//        db.setTimestamp(timestamp);
//
//        return db;
//    }

    private static Vehicle buildVehicle(final String id,
                                        final String name,
                                        final String number,
                                        final String date,
                                        final boolean status,
                                        final int quantity,
                                        final String description) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setName(name);
        vehicle.setNumber(number);
        vehicle.setStatus(status);
        vehicle.setQuantity(quantity);
        vehicle.setDescription(description);
        try {
            vehicle.setCreated(SIMPLE_DATE_FORMAT.parse(date));
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }

        return vehicle;
    }

}

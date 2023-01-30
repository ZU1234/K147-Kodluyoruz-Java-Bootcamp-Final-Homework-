package com.biletx.service;

import com.biletx.converter.BasketConverter;
import com.biletx.converter.VehicleConverter;
import com.biletx.enums.GenderType;
import com.biletx.enums.UserType;
import com.biletx.enums.VehicleStatus;
import com.biletx.enums.VehicleType;
import com.biletx.exception.TicketDoesNotException;
import com.biletx.exception.UserDoesNotException;
import com.biletx.exception.VehicleDoesNotException;
import com.biletx.model.Basket;
import com.biletx.model.User;
import com.biletx.model.Vehicle;
import com.biletx.repository.TicketRepository;
import com.biletx.repository.VehicleRepository;
import com.biletx.request.RouteRequest;
import com.biletx.request.TicketRequest;
import com.biletx.response.BasketResponse;
import com.biletx.response.TotalTicketsAndTotalPricesByVehicleResponse;
import com.biletx.response.VehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VehicleService {
    private static final long MAX_TICKETS_FOR_INDIVIDUAL_USERS = 5;
    private static final long MAX_MALE_PASSENGERS_SINGLE_ORDER_FOR_INDIVIDUAL_USERS = 2;
    private static final long MAX_TICKETS_FOR_CORPORATE_USERS = 20;
    private final Logger logger = Logger.getLogger(Vehicle.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VehicleConverter vehicleConverter;

    @Autowired
    private BasketConverter basketConverter;


    public VehicleResponse save(Vehicle vehicle) {

        vehicle.setNo(vehicle.getNo().toUpperCase());
        vehicle.setFromWhere(vehicle.getFromWhere().substring(0, 1).toUpperCase()
                + vehicle.getFromWhere().substring(1).toLowerCase());
        vehicle.setWhereTo(
                vehicle.getWhereTo().substring(0, 1).toUpperCase() + vehicle.getWhereTo().substring(1).toLowerCase());

        switch (vehicle.getVehicleType()) {
            case CAR -> {

                vehicle.setRidership(45);
                vehicle.setEmptySeat(vehicle.getRidership());
                vehicleRepository.save(vehicle);
                logger.log(Level.INFO, "VehicleType : " + vehicle.getVehicleType() + " seferi oluşturuldu.");
            }

            case PLANE -> {

                vehicle.setRidership(189);
                vehicle.setEmptySeat(vehicle.getRidership());
                vehicleRepository.save(vehicle);
                logger.log(Level.INFO, "VehicleType : " + vehicle.getVehicleType() + " seferi oluşturuldu.");

            }
            default -> {
                logger.log(Level.WARNING, "VehicleType : " + vehicle.getVehicleType() + " seferi oluşturulamadı.");
                throw new IllegalStateException("Vehicle type : " + vehicle.getVehicleType() + " seferi " +
                        "oluşturulamadı.");
            }
        }

        return vehicleConverter.convert(vehicle);
    }

    public List<VehicleResponse> getAll() {
        List<VehicleResponse> responses=vehicleConverter.convert(vehicleRepository.findAll());
        if (responses.isEmpty()) {
            logger.log(Level.WARNING,"Sefer bulunmamaktadır.");
            throw new VehicleDoesNotException("Sefer bulunmamaktadır.");
        }
        logger.log(Level.INFO,"Tüm seferler listelendi");
        return responses;
    }

    public List<VehicleResponse> getAllByActiveVehicle() {
        List<VehicleResponse> responses=vehicleConverter.convert(vehicleRepository.findAllByActive());
        if (responses.isEmpty()) {
            logger.log(Level.WARNING,"Aktif sefer bulunmamaktadır.");
            throw new VehicleDoesNotException("Aktif sefer bulunmamaktadır.");
        }
        logger.log(Level.INFO,"Tüm aktif seferler listelendi");
        return responses;
    }

    public Vehicle getById(int id) {
        return vehicleRepository.findById(id).orElseThrow(() -> {
            logger.log(Level.WARNING, "Vehicle id : {0} does not exist.", id);
            throw new VehicleDoesNotException("Vehicle id : " + id + "does " + "not exist.");
        });
    }

    @Cacheable(value = "getAll", key = "#id")
    public VehicleResponse cancellationVehicle(Integer id) {

        Vehicle vehicle = getById(id);
        vehicle.setStatus(VehicleStatus.CANCELLED);
        vehicleRepository.save(vehicle);
        logger.log(Level.INFO,"id : {0} Sefer iptal edildi.",id);
        return vehicleConverter.convert(vehicle);
    }

    //@Cacheable(value="getAll",key = "#id") TODO cahable
    public List<VehicleResponse> getAllByRoute(RouteRequest route) {
        List<VehicleResponse> responses=vehicleConverter.convert(vehicleRepository.findAllByProvince(route.getFromWhere(), route.getWhereTo()));
        if(responses.isEmpty()){
            logger.log(Level.WARNING,"Rotası : {0} sefer bulunmamaktadır."+route);
            throw new VehicleDoesNotException("Rotası : "+route+" sefer bulunmamaktadır.");
        }
        logger.log(Level.INFO,"Rotası : {0} olan tüm seferler listelendi."+route);
        return responses;
    }

    public List<VehicleResponse> getAllByDate(String departureTime) {
        if (!Objects.equals("dd-MM-yyyy", departureTime.formatted())) {
            logger.log(Level.WARNING, "tarih formatı \"dd-MM-yyyy\" seklinde olmalidir.");
            throw new VehicleDoesNotException("tarih formatı \"dd-MM-yyyy\" seklinde olmalidir.");
        }
        List<VehicleResponse> responses = vehicleConverter.convert(vehicleRepository.findAllByDate(departureTime));
        if (responses.isEmpty()) {
            logger.log(Level.WARNING,  "{0} tarihine ait sefer bulunmamaktadır.", departureTime);
            throw new VehicleDoesNotException(departureTime + " tarihine ait sefer bulunmamaktadır.");
        }
        logger.log(Level.INFO,  "{0} tarihine ait tüm seferler listelendi.", departureTime);
        return responses;
    }

    public List<VehicleResponse> getAllByVehicleType(VehicleType vehicleType) {

        List<VehicleResponse> responses = vehicleConverter.convert(vehicleRepository.findAll().stream()
                .filter(type -> vehicleType.equals(type.getVehicleType()))
                .toList());
        if (responses.isEmpty()) {
            throw new VehicleDoesNotException(vehicleType + " türünde sefer bulunmamaktadır.");
        }
        logger.log(Level.INFO,  "{0} türünde tüm seferler listelendi.", vehicleType);
        return responses;

    }

    // araç kimliğine göre sepetteki siparişlerin sayısı
    public long countSepetByVehicleId(int vehicleId, int userId) {
        long response= userService.getSepet(userId).stream()
                .filter(s -> Objects.equals(vehicleId, s.getVehicle().getId())).count();
        logger.log(Level.INFO,  "vehicle_id : {0} ait sepetteki bilet sayısı {1} ",
                new Integer[]{vehicleId, userId});
        return response;
    }

    // Sepete ekleme
    public List<BasketResponse> sepetAdd(TicketRequest ticket) {
        User foundUser = userService.getById(ticket.getUserId());
        long totalTicket = getTotalTicketByVehicleId(ticket.getVehicleId(), foundUser.getId());
        long emptySeat = getById(ticket.getVehicleId()).getEmptySeat();

        if (getById(ticket.getVehicleId()).getEmptySeat() == 0) {
            logger.log(Level.WARNING, "Urun stokta yok.");
            throw new VehicleDoesNotException("Urun stokta yok.");
        }
        if ((totalTicket == emptySeat)) {
            logger.log(Level.WARNING, "Urun stokta sayısını gecemezsiniz. stok sayisi : {0}", emptySeat);
            throw new VehicleDoesNotException("Urun stokta sayısını gecemezsiniz.");
        }

        // induvidual kullanıcı
        if (userService.isIndividual(foundUser.getId())) {
            if (GenderType.MALE.equals(ticket.getGender())) {
                isMaximumNumberOfMaleOrders(foundUser, MAX_MALE_PASSENGERS_SINGLE_ORDER_FOR_INDIVIDUAL_USERS);
            }
            if (isMaxTicket(totalTicket, MAX_TICKETS_FOR_INDIVIDUAL_USERS, foundUser.getType())) {
                userService.addTicketInBasket(convertBasket(ticket));
                logger.log(Level.INFO, "Urun sepete eklendi. Urun id : " + ticket.getVehicleId());
                return basketConverter.convert(userService.getSepet(ticket.getUserId()));
            }
        } else {
            // corporator kullanıcı
            if (userService.isCorporate(foundUser.getId())) {
                if (isMaxTicket(totalTicket, MAX_TICKETS_FOR_CORPORATE_USERS, foundUser.getType())) {
                    userService.addTicketInBasket(convertBasket(ticket));
                    logger.log(Level.INFO, "Urun sepete eklendi. Urun id : " + ticket.getVehicleId());
                    return basketConverter.convert(userService.getSepet(ticket.getUserId()));
                }

            }
        }

        return null;
    }

    // sepet converter
    public Basket convertBasket(TicketRequest request) {
        Basket basket = new Basket();
        basket.setVehicle(getById(request.getVehicleId()));
        basket.setUserId(request.getUserId());
        basket.setGender(request.getGender());
        basket.setPassengerName(request.getPassengerName());
        return basket;
    }


    // Maksimum Erkek Sipariş Sayısı
    private boolean isMaximumNumberOfMaleOrders(User user, long maxNumberMaleSingleOrderuserstRule) {
        if (numberOfMenInTheBasket(user.getId()) == maxNumberMaleSingleOrderuserstRule) {
            logger.log(Level.WARNING, "Tek sipariste en fazla " + maxNumberMaleSingleOrderuserstRule + " erkek yolcu icin bilet alabilirsiniz!");
            throw new UserDoesNotException("Tek sipariste en fazla " + maxNumberMaleSingleOrderuserstRule + " erkek yolcu icin bilet alabilirsiniz!");
        }
        return true;

    }

    // max bilet kontrolü
    private boolean isMaxTicket(long totalTicket, long maxNumberTicketRule, UserType userType) {
        if (totalTicket == maxNumberTicketRule) {
            logger.log(Level.WARNING, userType + " kullanicilar ayni sefer icin en fazla " + maxNumberTicketRule + " bilet alabilir! = ");
            throw new UserDoesNotException(userType + " kullanicilar ayni sefer icin en fazla " + maxNumberTicketRule + " bilet alabilir! = ");

        }
        return true;
    }

    // araç Kimliğine Göre Toplam Bileti getir
    private long getTotalTicketByVehicleId(int vehicleId, int userId) {
        long countTicketInBasket = countSepetByVehicleId(vehicleId, userId);
        long countBuyingTicket = numberOfTicketsAccordingToVehicleIdAndUserId(vehicleId, userId);
        return countBuyingTicket + countTicketInBasket;
    }

    // araç Kimliği ve Kullanıcı Kimliğine Göre Bilet Sayısı
    private long numberOfTicketsAccordingToVehicleIdAndUserId(int vehicleId, int userId) {
        return ticketRepository.findAll().stream().filter(vehicle -> vehicle.getUser().getId().equals(userId))
                .filter(vehicle -> vehicle.getVehicle().getId().equals(vehicleId))
                .count();
    }

    // sepetteki Adam Sayısı
    public long numberOfMenInTheBasket(int userId) {
        return getAllBasketByUserId(userId).stream()
                .filter(basket -> basket.getGender().equals(GenderType.MALE)).count();
    }

    // sepetteki tüm ürünleri

    public List<BasketResponse> getAllBasketByUserId(int userId) {
        return basketConverter.convert(userService.getSepet(userId));
    }

    public TotalTicketsAndTotalPricesByVehicleResponse totalTicketsAndTotalPricesByVehicle(int vehicleId) {
        if (ticketRepository.TotalTicketsByVehicle(vehicleId) == null || ticketRepository.TotalPricesByVehicle(vehicleId) == null) {
            logger.log(Level.WARNING, "vehicleId : {0} Sefere ait satın alınmış bilet bulunmamaktadır."+vehicleId);
            throw new TicketDoesNotException("Sefere ait satın alınmış bilet bulunmamaktadır.");
        }
        long countTickets = ticketRepository.TotalTicketsByVehicle(vehicleId);
        long countPrices = ticketRepository.TotalPricesByVehicle(vehicleId);

        TotalTicketsAndTotalPricesByVehicleResponse response = new TotalTicketsAndTotalPricesByVehicleResponse();
        response.setTotalTickets(countTickets);
        response.setTolalPrices(countPrices);
        return response;
    }

}

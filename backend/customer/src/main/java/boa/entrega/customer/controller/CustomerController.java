package boa.entrega.customer.controller;

import boa.entrega.customer.model.Address;
import boa.entrega.customer.model.Customer;
import boa.entrega.customer.service.CustomerService;
import boa.entrega.customer.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtUtils jwtUtils;

    @Operation(description = "Obtem as informações cadastrais do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("me")
    public ResponseEntity<Customer> getCustomer(@Parameter(hidden = true) @RequestHeader String authorization) {
        long customerId = Long.parseLong(jwtUtils.getClaim(authorization, "customer_id").toString());

        Customer response = customerService.getCustomer(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Atualiza os endereços do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista atualizada de endereços"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("address")
    public ResponseEntity<List<Address>> updateAddresses(@Parameter(hidden = true) @RequestHeader String authorization,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de endereços a serem atualizados") @RequestBody List<Address> body) {
        long customerId = Long.parseLong(jwtUtils.getClaim(authorization, "customer_id").toString());

        List<Address> response = customerService.updateAddresses(customerId, body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

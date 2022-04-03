package com.tarbus.payload.response;

import com.tarbus.payload.entity.Company;
import com.tarbus.payload.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserResponse {
  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private String phone;
  private Set<Role> roles;
  private Set<Company> companies;
}

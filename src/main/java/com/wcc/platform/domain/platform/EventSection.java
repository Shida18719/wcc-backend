package com.wcc.platform.domain.platform;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wcc.platform.domain.cms.attributes.LabelLink;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** EventSection representing list of events {@link Event}. */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class EventSection {
  @NotBlank private String title;

  @JsonInclude(Include.NON_NULL)
  @NotBlank
  private LabelLink link;

  private List<Event> events;
}

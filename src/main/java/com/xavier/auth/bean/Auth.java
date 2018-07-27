package com.xavier.auth.bean;

import lombok.*;

import java.io.Serializable;

/**
 * Auth Bean
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Auth implements Serializable {

	/**
	 * 系统名
	 */
	private String systemName;

	/**
	 * 请求url
	 */
	private String authUrl;
}

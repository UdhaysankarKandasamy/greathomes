/**
 * 
 */
package com.udhay.self;

import java.util.List;

import lombok.Data;

/**
 * @author udhay
 *
 */

@Data
public class Request {

	private String object;
	private List<Entry> entry;

}

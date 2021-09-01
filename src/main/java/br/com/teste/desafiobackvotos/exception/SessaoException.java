/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.exception;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author deivid
 */
@Slf4j
public class SessaoException extends Exception {

    public SessaoException(String message) {
        super(message);
        log.error("sessao :{}", message);
    }

}

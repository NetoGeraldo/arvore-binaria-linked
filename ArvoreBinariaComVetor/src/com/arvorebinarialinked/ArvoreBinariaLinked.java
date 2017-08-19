package com.arvorebinarialinked;

import java.util.Collection;

/**
 *
 * @author 1161113875
 */
public class ArvoreBinariaLinked<Chave extends Comparable<Chave>, Valor> implements IArvoreBinariaLinked {
    
    private No<Chave, Valor> raiz;
    
    
    public ArvoreBinariaLinked() {    }
    
    
    @Override
    public No inserir(Comparable chave, Object valor, No pai, Lado lado) {
        
        No<Chave, Valor> novoNo = new No(chave, valor);
        No<Chave, Valor> aux = this.raiz;
        
        if (this.raiz == null) {
            this.raiz = novoNo;
            return novoNo;
        } else {
            
            aux.setPai(pai);
            if (lado == Lado.ESQUERDO) {
                if (aux.getFilhoEsquerdo() == null) {
                    aux.setFilhoEsquerdo(novoNo);
                    return novoNo;
                }
            }
            
            if (lado == Lado.DIREITO) {
                if (aux.getFilhoDireito() == null) {
                    aux.setFilhoDireito(novoNo);
                    return novoNo;
                }
            }
            
        }
        
        return null;
        
    }

    @Override
    public No obterFilho(No pai, Lado lado) {
        
        if (lado == Lado.ESQUERDO) {
            return pai.getFilhoEsquerdo();
        }
        
        if (lado == Lado.DIREITO) {
            return pai.getFilhoDireito();
        }
        
        return null;
    }

    @Override
    public No obterFilho(Comparable chave, Lado lado) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public No remover(No no) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public No remover(No pai, Lado lado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public No remover(Comparable chave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection obterValores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void percursoPreOrdem(No<Chave, Valor> no) {
        
        if (no == null) {
            return;
        }
        
        System.out.println("Valor: " + no.getValor());
        
        if (no.getFilhoEsquerdo() != null) {
            this.percursoPreOrdem(no.getFilhoEsquerdo());
        }
        
        if (no.getFilhoDireito() != null) {
            this.percursoPreOrdem(no.getFilhoDireito());
        }
        
    }
    
    public void percursoPosOrdem(No<Chave, Valor> no) {
        
        if (no == null) {
            return;
        }
        
        if (no.getFilhoEsquerdo() != null) {
            this.percursoPosOrdem(no.getFilhoEsquerdo());
        }
        
        if (no.getFilhoDireito() != null) {
            this.percursoPosOrdem(no.getFilhoDireito());
        }
        
        System.out.println("Valor: " + no.getValor());
        
    }
    
    public void percursoEmOrdem(No<Chave, Valor> no) {
        
        if (no == null) {
            return;
        }
        
        if (no.getFilhoEsquerdo() != null) {
           this.percursoEmOrdem(no.getFilhoEsquerdo());
        }
        
        System.out.println("Valor: " + no.getValor());
        
        if (no.getFilhoDireito() != null) {
            this.percursoEmOrdem(no.getFilhoDireito());
        }
        
   }
    
}

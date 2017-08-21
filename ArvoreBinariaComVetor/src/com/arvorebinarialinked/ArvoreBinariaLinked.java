package com.arvorebinarialinked;

import java.util.ArrayList;
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
        No<Chave,Valor> noPai = null;
        
        for (Object no : this.obterValores()) {
            if (( (No<Chave, Valor>) no ).getChave().equals(no)) {
                noPai = (No<Chave, Valor>) no;
                break;
            }
        }
        
        if (noPai != null) {
            
            if (lado == Lado.ESQUERDO) {
                return noPai.getFilhoEsquerdo();
            } else {
                return noPai.getFilhoDireito();
            }
        
        }
        
        return null;
        
    }

    @Override
    public No remover(No no) {
        
        No<Chave, Valor> noAux;
        
        if (no.getFilhoEsquerdo() == null && no.getFilhoDireito() != null) {
            
            no.getFilhoDireito().setPai(no.getPai());
            if (this.ladoDoFilho(no) == Lado.ESQUERDO) {
                no.getPai().setFilhoEsquerdo(no.getFilhoDireito());
            } else {
                no.getPai().setFilhoDireito(no.getFilhoDireito());
            }
            
            return no;
            
        } else if (no.getFilhoEsquerdo() != null && no.getFilhoDireito() == null) {
            
            no.getFilhoEsquerdo().setPai(no.getPai());
            if (this.ladoDoFilho(no) == Lado.ESQUERDO) {
                no.getPai().setFilhoEsquerdo(no.getFilhoEsquerdo());
            } else {
                no.getPai().setFilhoDireito(no.getFilhoEsquerdo());
            }
            
            return no;
            
        } else {
            return null;
        }
        
    }
    
    private Lado ladoDoFilho(No<Chave, Valor> noFilho) {
        if (noFilho.getPai().getFilhoEsquerdo().equals(noFilho)) {
            return Lado.ESQUERDO;
        } else {
            return Lado.DIREITO;
        }
    }

    @Override
    public No remover(No pai, Lado lado) {
        if (lado == Lado.ESQUERDO) {
            return this.remover(pai.getFilhoEsquerdo());
        } else {
            return this.remover(pai.getFilhoDireito());
        }
    }

    @Override
    public No remover(Comparable chave) {
        
        for (Object no : this.obterValores()) {
            if (((No<Chave, Valor>) no).getChave().equals(chave)) {
                return this.remover(((No<Chave, Valor>) no));
            }
        }
        
        return null;
    }

    @Override
    public Collection obterValores() {
        
        ArrayList<No<Chave,Valor>> lista = new ArrayList<>();
        
        this.listarPercurso(this.raiz, lista);
        
        return lista;
        
    }
    
    private void listarPercurso(No<Chave, Valor> no, Collection collection) {
        
        if (no == null) {
            return;
        }
        
        collection.add(no);
        
        if (no.getFilhoEsquerdo() != null) {
            listarPercurso(no.getFilhoEsquerdo(), collection);
        }
        
        if (no.getFilhoDireito() != null) {
            listarPercurso(no.getFilhoDireito(), collection);
        }
        
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

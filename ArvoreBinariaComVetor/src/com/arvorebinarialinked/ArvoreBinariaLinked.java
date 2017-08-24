package com.arvorebinarialinked;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author 1161113875
 */
public class ArvoreBinariaLinked<Chave extends Comparable<Chave>, Valor> implements IArvoreBinariaLinked<Chave, Valor> {
    
    private No<Chave, Valor> raiz;
    
    
    public ArvoreBinariaLinked() {    }
    
    
    @Override
    public No inserir(Comparable chave, Object valor, No pai, Lado lado) {
        
        No<Chave, Valor> novoNo = new No(chave, valor);
        
        if (this.raiz == null) {
            this.raiz = novoNo;
            return novoNo;
        } else {
            
            novoNo.setPai(pai);
            
            if (lado == Lado.ESQUERDO) {
                if (pai.getFilhoEsquerdo() == null) {
                    pai.setFilhoEsquerdo(novoNo);
                    return novoNo;
                }
            }
            
            if (lado == Lado.DIREITO) {
                if (pai.getFilhoDireito() == null) {
                    pai.setFilhoDireito(novoNo);
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
            if (( (No<Chave, Valor>) no ).getChave().equals(chave)) {
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
        
        if (no.getFilhoDireito() == null && no.getFilhoEsquerdo() == null) {
            if (this.ladoDoFilho(no) == Lado.ESQUERDO) {
                no.getPai().setFilhoEsquerdo(null);
                no.setPai(null);
                
                return no;
            } else {
                no.getPai().setFilhoDireito(null);
                no.setPai(null);
                
                return no;
            }
        }
        
        if (no.getFilhoEsquerdo() == null) {
            
            no.getFilhoDireito().setPai(no.getPai());
            if (this.ladoDoFilho(no) == Lado.ESQUERDO) {
                no.getPai().setFilhoEsquerdo(no.getFilhoDireito());
            } else {
                no.getPai().setFilhoDireito(no.getFilhoDireito());
            }
            
            return no;
            
        } else if (no.getFilhoDireito() == null) {
            
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
        
    private Lado ladoDoFilho(No<Chave, Valor> noFilho) {
        if (noFilho.getPai().getFilhoEsquerdo() != null && 
                noFilho.getPai().getFilhoEsquerdo().equals(noFilho)) {
            return Lado.ESQUERDO;
        } else {
            return Lado.DIREITO;
        }
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
    
    public No<Chave, Valor> getRaiz() {
        return raiz;
    }
    
}
